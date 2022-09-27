package com.balgun.hesap.controller;

import com.balgun.hesap.dto.ExchangeDto;
import com.balgun.hesap.dto.HesapDto;
import com.balgun.hesap.dto.KullaniciDto;
import com.balgun.hesap.entity.Hesap;
import com.balgun.hesap.service.HesapDetayService;
import com.balgun.hesap.service.HesapService;
import com.balgun.hesap.service.KullaniciService;
import lombok.extern.flogger.Flogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hesap")
@CrossOrigin()
@Slf4j
public class HesapController {
    @Autowired
    HesapService hesapService;
    @Autowired
    KullaniciService  kullaniciService;



    @GetMapping("/{userid}")
    ResponseEntity<List<HesapDto>> getAccountsByUserId(@PathVariable long userid)
    {
        return new ResponseEntity<>(hesapService.getHesapsByUserId(userid),HttpStatus.OK);
    }
    @GetMapping()
    ResponseEntity<List<HesapDto>> getAccountsWithJwt()
    {
        String  userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        KullaniciDto kullaniciDto=kullaniciService.getUserbyEmail(userDetails);

        return new ResponseEntity<>(hesapService.getHesapsByUserId(kullaniciDto.getId()),HttpStatus.OK);
    }
    @PostMapping("/exchange")
    @Transactional
    ResponseEntity<HesapDto> exchangeCurrency(@RequestBody ExchangeDto exchangeDto)
    {
        log.info(exchangeDto.toString());
           hesapService.Exchange(exchangeDto);

           // hesapService.BakiyeEkle(exchangeDto.getAlanHesapId(),exchangeDto.getAlimMiktari());
           // hesapService.BakiyeCikar(exchangeDto.getSatanHesapId(),exchangeDto.getSatimMiktari());

        return new ResponseEntity<>(hesapService.entityTODto(hesapService.getHesapByHesapId(exchangeDto.getSatanHesapId())),HttpStatus.OK);

    }
}
