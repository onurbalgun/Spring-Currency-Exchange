package com.balgun.hesap.dto;

import com.balgun.hesap.entity.ParaBirimi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OranDto {
    private ParaBirimi paraBirimi;
    private ParaBirimi paraBirimiTakas;
}