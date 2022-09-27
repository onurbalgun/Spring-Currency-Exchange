package com.balgun.hesap.repository;


import com.balgun.hesap.entity.HesapDetay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HesapDetayRepository extends JpaRepository<HesapDetay,Long> {
    List<HesapDetay> getHesapDetayByHesapId(Long hesapid);
}
