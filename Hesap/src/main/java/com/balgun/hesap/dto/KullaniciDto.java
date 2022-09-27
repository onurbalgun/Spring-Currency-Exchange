package com.balgun.hesap.dto;


import com.balgun.hesap.entity.Hesap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class KullaniciDto {

    private long id;
    private String email;

    private String fullName;

   // private List<HesapDto> hesapList;
}
