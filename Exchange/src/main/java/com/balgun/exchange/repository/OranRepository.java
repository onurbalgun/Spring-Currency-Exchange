package com.balgun.exchange.repository;

import com.balgun.exchange.entity.Oran;
import com.balgun.exchange.entity.ParaBirimi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OranRepository extends JpaRepository<Oran,Long> {
    Oran getOranByHesapTurAndHesapTakas(ParaBirimi hesaptur, ParaBirimi hesaptakas);

}
