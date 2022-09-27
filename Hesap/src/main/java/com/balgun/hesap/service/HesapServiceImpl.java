package com.balgun.hesap.service;

import com.balgun.hesap.dto.*;
import com.balgun.hesap.entity.Hesap;
import com.balgun.hesap.entity.HesapDetay;
import com.balgun.hesap.entity.Kullanici;
import com.balgun.hesap.repository.HesapRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;


import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HesapServiceImpl implements HesapService{

    @Autowired
    private HesapRepository hesapRepository;
    @Autowired
    HesapDetayService hesapDetayService;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ModelMapper modelMapper;

    public Hesap saveHesap(Hesap hesap){
        return hesapRepository.save(hesap);
    }



    public List<HesapDto> getHesapsByUserId(long userid)
    {
        List<Hesap> hesapList = hesapRepository.getHesapByUserId(userid);
        if (CollectionUtils.isEmpty(hesapList)) {
            return new ArrayList<>();
        }
        List<HesapDto> hesapDtoList=new ArrayList<>() ;
        for (Hesap entity : hesapList) {
            HesapDto hesapDto  = entityTODto(entity);//model
            hesapDtoList.add(hesapDto);
            //TODO attach hesaplist to users
        }
    return hesapDtoList;
    }
    @Transactional
   public Hesap BakiyeEkle(Long hesapid, BigDecimal bakiye)
    {
        Hesap hesap=hesapRepository.getHesapById(hesapid);

            hesap.setBakiye(hesap.getBakiye().add(bakiye));

            hesapRepository.save(hesap);//Açıklama olarak da alış yapılan hesaba Alış [Alınan Miktar] @ [Alım Fiyatı], satış yapılan hesaba Satış [Satılan Miktar] @ [Satış Fiyatı] olarak yansıtılacaktır.
            return hesap;

    }
    @Transactional
    public Hesap BakiyeCikar(Long hesapid, BigDecimal bakiye)
    {
        Hesap hesap=hesapRepository.getHesapById(hesapid);

            hesap.setBakiye(hesap.getBakiye().subtract(bakiye));

        hesapRepository.save(hesap);
        //TODO call hesapdetayrepository add log of the transaction
        return hesap;
    }
    public void Exchange(ExchangeDto exchangeDto)
    {
        double oran= 0;
        OranDto oranDto = new OranDto();
        oranDto.setParaBirimiTakas(exchangeDto.getAlanHesapPB());
        oranDto.setParaBirimi(exchangeDto.getSatanHesapPB());
        if (exchangeDto.getAlanHesapPB().equals(exchangeDto.getSatanHesapPB()))
        {
           oran =1;

        }else {
            Exchange1Dto exchange1Dto = restTemplate.postForObject(
                    "http://localhost:8081/api/v1/oran",
                    oranDto,
                    Exchange1Dto.class);
            oran=exchange1Dto.getOran();

        }//1000 miktar 17,73oran

        //TODO call exchange service get rates T postForObject(URI url, Object request, Class<T> responseType)

        BigDecimal AlinanMiktar = exchangeDto.getMiktar() ;
        BigDecimal SatilanMiktar = exchangeDto.getMiktar().divide(new BigDecimal(oran), MathContext.DECIMAL32);
        Hesap satanHesap=  getHesapByHesapId(exchangeDto.getSatanHesapId());


            if ((satanHesap.getBakiye()).compareTo(SatilanMiktar)>=1) {

                BakiyeEkle(exchangeDto.getAlanHesapId(), AlinanMiktar);
                hesapDetayService.saveHesapDetay(new HesapDetay(null,exchangeDto.getAlanHesapId(),"Alış "+AlinanMiktar+"@Alış Oranı "+oran,AlinanMiktar,new Date()));
                BakiyeCikar(exchangeDto.getSatanHesapId(), SatilanMiktar);
                hesapDetayService.saveHesapDetay(new HesapDetay(null,exchangeDto.getSatanHesapId(),"-Satış "+SatilanMiktar+"@Satış oranı:"+oran,SatilanMiktar,new Date()));
            }
    }
    public Hesap getHesapByHesapId(Long hesapid)
    {
        return hesapRepository.getHesapById(hesapid);
    }

    public HesapDto entityTODto(Hesap hesap) {
        HesapDto hesapDto = modelMapper.map(hesap, HesapDto.class);
        return hesapDto;
    }


    public Hesap DtoToEntity(HesapDto hesapDto) {
        Hesap hesap = modelMapper.map(hesapDto, Hesap.class);
        return hesap;
    }

}
