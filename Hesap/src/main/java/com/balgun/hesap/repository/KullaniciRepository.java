package com.balgun.hesap.repository;

import com.balgun.hesap.dto.KullaniciDto;
import com.balgun.hesap.entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KullaniciRepository extends JpaRepository<Kullanici,Long> {
    Optional<Kullanici> findByEmail(String email);
}
