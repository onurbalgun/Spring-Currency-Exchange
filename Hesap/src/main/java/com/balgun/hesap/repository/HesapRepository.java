package com.balgun.hesap.repository;

import com.balgun.hesap.entity.Hesap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HesapRepository extends JpaRepository<Hesap,Long> {
    List<Hesap> getHesapByUserId(Long userid);
    Hesap getHesapById(Long id);
}
