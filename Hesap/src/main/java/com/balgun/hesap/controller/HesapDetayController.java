package com.balgun.hesap.controller;

import com.balgun.hesap.entity.HesapDetay;
import com.balgun.hesap.service.HesapDetayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hesapdetay")
@Slf4j
public class HesapDetayController {
    @Autowired
    HesapDetayService hesapDetayService;
    @GetMapping("/{hesapid}")
    public List<HesapDetay> getHesapDetaysByHesapId(@PathVariable Long hesapid)
    {
        return hesapDetayService.getHesapDetayByHesapid(hesapid);
    }
}
