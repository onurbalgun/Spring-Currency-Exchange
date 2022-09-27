package com.balgun.hesap.dto;

import com.balgun.hesap.entity.ParaBirimi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeDto {

    private Long alanHesapId;
    private Long satanHesapId;
    private ParaBirimi alanHesapPB;
    private ParaBirimi satanHesapPB;
    private BigDecimal miktar;

}
