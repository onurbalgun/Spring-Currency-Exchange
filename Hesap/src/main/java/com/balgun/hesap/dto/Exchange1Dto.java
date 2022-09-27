package com.balgun.hesap.dto;

import com.balgun.hesap.entity.ParaBirimi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor

public class Exchange1Dto {

    private Long id;
    private ParaBirimi hesapTur;
    private ParaBirimi hesapTakas;
    private double oran;
}
