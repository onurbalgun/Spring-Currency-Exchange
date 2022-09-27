package com.balgun.hesap;

import com.balgun.hesap.entity.Hesap;
import com.balgun.hesap.entity.HesapDetay;
import com.balgun.hesap.entity.Kullanici;
import com.balgun.hesap.entity.ParaBirimi;
import com.balgun.hesap.service.HesapDetayService;
import com.balgun.hesap.service.HesapService;
import com.balgun.hesap.service.KullaniciService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class HesapApplication {
    public static void main(String[] args) {
        SpringApplication.run(HesapApplication.class,args);

    }
    @Bean
    CommandLineRunner run(KullaniciService kullaniciService, HesapService hesapService, HesapDetayService hesapDetayService){
        return args -> {
         Kullanici testKullanici= kullaniciService.saveKullanici(new Kullanici(null,"onur.balgun@gmail.com", "password123","Onur Balgün"));
          kullaniciService.saveKullanici(new Kullanici(null,"onur.balgun2@gmail.com", "password123","Onur2 Balgün"));
          kullaniciService.saveKullanici(new Kullanici(null,"onur.balgun3@gmail.com", "password123","Onur3 Balgün"));
         Hesap hesap= hesapService.saveHesap(new Hesap(null,testKullanici.getId(), ParaBirimi.TL,"1234545",new BigDecimal("9000.10")));
          hesapService.saveHesap(new Hesap(null,testKullanici.getId(),ParaBirimi.TL,"1234566",new BigDecimal("1300")));
          hesapService.saveHesap(new Hesap(null,testKullanici.getId(),ParaBirimi.EURO,"1234567",new BigDecimal("125.50")));
          hesapService.saveHesap(new Hesap(null,testKullanici.getId(),ParaBirimi.DOLAR,"12345678",new BigDecimal("1225")));
          hesapDetayService.saveHesapDetay(new HesapDetay(null,hesap.getId(),"ALIŞ",new BigDecimal("10"),new Date()));
          hesapDetayService.saveHesapDetay(new HesapDetay(null,hesap.getId(),"ALIŞ",new BigDecimal("100"),new Date()));
          hesapDetayService.saveHesapDetay(new HesapDetay(null,hesap.getId(),"SATIŞ",new BigDecimal("-210"),new Date()));


        };
    }
}