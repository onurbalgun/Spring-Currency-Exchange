package com.balgun.exchange.service;

import com.balgun.exchange.entity.Oran;
import com.balgun.exchange.entity.ParaBirimi;

public interface OranService {
    Oran saveOran(Oran oran);

    Oran getOranByHesapTurAndHesapTakas(ParaBirimi hesaptur, ParaBirimi hesaptakas);
}
