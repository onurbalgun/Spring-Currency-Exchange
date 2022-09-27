package com.balgun.exchange.controller;

import com.balgun.exchange.dto.OranDondurDto;
import com.balgun.exchange.entity.Oran;
import com.balgun.exchange.service.OranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/oran")
@CrossOrigin
public class OranController {
    @Autowired
    OranService oranService;
    @PostMapping()
    public ResponseEntity<Oran> oranDondur(@RequestBody OranDondurDto oranDondurDto)
    {

       return new ResponseEntity<>( oranService.getOranByHesapTurAndHesapTakas( oranDondurDto.getParaBirimi(),oranDondurDto.getParaBirimiTakas()),HttpStatus.OK);
    }
}
