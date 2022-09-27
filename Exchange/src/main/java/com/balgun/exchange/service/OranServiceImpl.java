package com.balgun.exchange.service;

import com.balgun.exchange.entity.Oran;
import com.balgun.exchange.entity.ParaBirimi;
import com.balgun.exchange.repository.OranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OranServiceImpl implements OranService{
    @Autowired
    public OranRepository oranRepository;
    public Oran saveOran(Oran oran)
    {
   return oranRepository.save(oran);
    }
   public Oran getOranByHesapTurAndHesapTakas(ParaBirimi hesaptur, ParaBirimi hesaptakas){
        return oranRepository.getOranByHesapTurAndHesapTakas(hesaptur,hesaptakas);
    }
}
