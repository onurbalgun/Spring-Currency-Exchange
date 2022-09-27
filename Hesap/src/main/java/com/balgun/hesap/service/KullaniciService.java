package com.balgun.hesap.service;

import com.balgun.hesap.dto.KullaniciDto;
import com.balgun.hesap.dto.KullaniciLoginDto;
import com.balgun.hesap.entity.Kullanici;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;


public interface KullaniciService {

    KullaniciDto userLogin(KullaniciLoginDto kullaniciLoginDto);
    //CRUD

    Kullanici saveKullanici(Kullanici kullanici);
    public KullaniciDto createUser(Kullanici kullanici);
    public KullaniciDto getUserById(Long id);

    public KullaniciDto getUserbyEmail(String email);

    //model mapper
    public KullaniciDto entityTODto(Kullanici kullaniciModel);
    public Kullanici DtoToEntity(KullaniciDto kullaniciDto);
}
