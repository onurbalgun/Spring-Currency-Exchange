package com.balgun.exchange.dto;

import com.balgun.exchange.entity.ParaBirimi;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OranDondurDto {
    private ParaBirimi paraBirimi;
    private ParaBirimi paraBirimiTakas;

}
