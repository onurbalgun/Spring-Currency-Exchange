package com.balgun.hesap.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.balgun.hesap.dto.KullaniciDto;
import com.balgun.hesap.dto.KullaniciLoginDto;
import com.balgun.hesap.entity.Kullanici;
import com.balgun.hesap.repository.KullaniciRepository;
import com.balgun.hesap.service.KullaniciService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
@CrossOrigin()
public class KullaniciController {
    @Autowired
    KullaniciService kullaniciService;

    @PostMapping("/login")
    public ResponseEntity<KullaniciDto> loginUser(@RequestBody KullaniciLoginDto kullaniciLoginDto)
    {

        return new ResponseEntity<>(kullaniciService.userLogin(kullaniciLoginDto), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<KullaniciDto> createUser(@RequestBody Kullanici kullanici) {
        return new ResponseEntity<>(kullaniciService.createUser(kullanici), HttpStatus.CREATED);
    }
    @GetMapping("/refreshtoken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader!= null&& authorizationHeader.startsWith("Bearer "))
        {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm=Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String email=decodedJWT.getSubject();
                KullaniciDto kullaniciDto = kullaniciService.getUserbyEmail(email);
                String acces_token = JWT.create().withSubject(kullaniciDto.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() +100*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .sign(algorithm);
                //  response.setHeader("acces_token",acces_token);
                //  response.setHeader("refresh_token",refresh_token);
                Map<String ,String> tokens=new HashMap<>();
                tokens.put("acces_token",acces_token);
                tokens.put("refresh_token",refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),tokens);


            }
            catch (Exception exception)
            {
                log.error("Error refreshing token:{}",exception.getMessage());
                response.setHeader("error",exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String ,String> error=new HashMap<>();
                error.put("error",exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),error);

            }
        }
        else {
           throw new RuntimeException("Refresh token is missing");
        }
    }


 //   public KullaniciDto createEmployee(@RequestBody KullaniciDto kullaniciDto){
 //       kullaniciService.createUser(kullaniciDto);
 //       return kullaniciDto;
 //   }

    @GetMapping("/{userid}")
    public KullaniciDto getUserById(@PathVariable long userid )
    {
        return kullaniciService.getUserById(userid);
    }
    @GetMapping("")
    public KullaniciDto getUserDetailsWithJwt()
    {
        String  userDetails =SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

        return kullaniciService.getUserbyEmail(userDetails);
    }
}