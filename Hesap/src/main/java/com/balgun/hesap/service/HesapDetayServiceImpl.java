package com.balgun.hesap.service;

import com.balgun.hesap.entity.Hesap;
import com.balgun.hesap.entity.HesapDetay;
import com.balgun.hesap.repository.HesapDetayRepository;
import com.balgun.hesap.repository.HesapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;

@Service
public class HesapDetayServiceImpl implements HesapDetayService{

    @Autowired
    private HesapDetayRepository hesapDetayRepository;


    public HesapDetay saveHesapDetay(HesapDetay hesapDetay) {
        return hesapDetayRepository.save(hesapDetay);
    }

    public List<HesapDetay> getHesapDetayByHesapid(Long id)
    {
      return   hesapDetayRepository.getHesapDetayByHesapId(id);
    }





}
