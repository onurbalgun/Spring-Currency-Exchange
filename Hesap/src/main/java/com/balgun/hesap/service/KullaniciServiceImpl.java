package com.balgun.hesap.service;

import com.balgun.hesap.dto.KullaniciDto;
import com.balgun.hesap.dto.KullaniciLoginDto;
import com.balgun.hesap.entity.Hesap;
import com.balgun.hesap.entity.Kullanici;
import com.balgun.hesap.repository.KullaniciRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class KullaniciServiceImpl implements KullaniciService, UserDetailsService {
    @Autowired
    private KullaniciRepository kullaniciRepository;
    @Autowired
    private HesapService hesapService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public Kullanici saveKullanici(Kullanici kullanici){
        kullanici.setPassword(passwordEncoder.encode(kullanici.getPassword()));
        return kullaniciRepository.save(kullanici);
    }
    public KullaniciDto userLogin(KullaniciLoginDto kullaniciLoginDto){
      Kullanici kullanici=  kullaniciRepository.findByEmail(kullaniciLoginDto.getEmail()).orElse(new Kullanici());

      if (!kullanici.getPassword().equals(kullaniciLoginDto.getPassword()))
      {
        return null;
     }
      return entityTODto(kullanici);
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       return kullaniciRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("Kullanici Bulunamadi"));
    }


    @Override
    public KullaniciDto createUser(Kullanici kullanici) {
        kullanici.setPassword(passwordEncoder.encode(kullanici.getPassword()));
        Kullanici kullanici1 = kullaniciRepository.save(kullanici);
        KullaniciDto kullaniciDto = entityTODto(kullanici1);//ModelMapper
        return kullaniciDto;
    }

    @Override
    public KullaniciDto getUserById(Long id) {
        Kullanici kullanici = kullaniciRepository
                .findById(id).orElseThrow();


        KullaniciDto kullaniciDto = entityTODto(kullanici);//model
        //kullaniciDto.setHesapList(hesapService.getAccountsByUserId(kullanici.getId()));

        return kullaniciDto;
    }
    public KullaniciDto getUserbyEmail(String email)
    {
        return entityTODto(kullaniciRepository.findByEmail(email).orElseThrow());
    }

    @Override
    public KullaniciDto entityTODto(Kullanici kullaniciModel) {
        KullaniciDto KullaniciDto = modelMapper.map(kullaniciModel, KullaniciDto.class);
        return KullaniciDto;
    }

    @Override
    public Kullanici DtoToEntity(KullaniciDto kullaniciDto) {
        Kullanici kullaniciModel = modelMapper.map(kullaniciDto, Kullanici.class);
        return kullaniciModel;
    }


}
