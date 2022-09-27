package com.balgun.hesap.dto;

import com.balgun.hesap.entity.HesapDetay;
import com.balgun.hesap.entity.ParaBirimi;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class HesapDto {

    private long id;
    private long userId;
    private ParaBirimi hesapTur;
    private String hesapNo;
    private BigDecimal bakiye;

   // private List<HesapDetayDto> hesapDetayList;


}