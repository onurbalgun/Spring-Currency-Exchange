package com.balgun.hesap.service;

import com.balgun.hesap.entity.Hesap;
import com.balgun.hesap.entity.HesapDetay;

import java.math.BigDecimal;
import java.util.List;

public interface HesapDetayService {
     HesapDetay saveHesapDetay(HesapDetay hesapDetay);
     List<HesapDetay> getHesapDetayByHesapid(Long id);



}
