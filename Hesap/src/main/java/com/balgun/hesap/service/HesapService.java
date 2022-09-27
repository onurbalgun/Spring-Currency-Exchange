package com.balgun.hesap.service;

import com.balgun.hesap.dto.ExchangeDto;
import com.balgun.hesap.dto.HesapDto;
import com.balgun.hesap.entity.Hesap;

import java.math.BigDecimal;
import java.util.List;

public interface HesapService {
    //CRUD
    List<HesapDto> getHesapsByUserId(long userid);
    Hesap saveHesap(Hesap hesap);
    Hesap BakiyeEkle(Long hesapid, BigDecimal bakiye);
    Hesap BakiyeCikar(Long hesapid, BigDecimal bakiye);
    Hesap getHesapByHesapId(Long hesapid);
    public void Exchange(ExchangeDto exchangeDto);
   //HesapDto createHesap(Hesap hesap);



    //model mapper
    HesapDto entityTODto(Hesap hesap);
     Hesap DtoToEntity(HesapDto hesapDto);

}
