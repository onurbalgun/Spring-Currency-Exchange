package com.balgun.hesap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.util.Date;

@Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Log4j2
    public class HesapDetayDto {

        private long    id;
        private String details;
        private BigDecimal totalSum;
        private Date date;
}