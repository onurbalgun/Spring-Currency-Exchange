package com.balgun.hesap.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.balgun.hesap.dto.KullaniciDto;
import com.balgun.hesap.dto.KullaniciLoginDto;
import com.balgun.hesap.entity.Kullanici;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {

        this.authenticationManager = authenticationManager;
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String requestData = request.getReader().lines().collect(Collectors.joining());

        Gson gson = new Gson();
        KullaniciLoginDto loginDto = gson.fromJson(requestData, KullaniciLoginDto.class);

      /*  String email = request.getParameter("email");
        String password = request.getParameter("password");
      log.info("Email is:{}",email);
      log.info("Password is:{}",password);*/
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword());
            return    authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        Kullanici user=(Kullanici) authentication.getPrincipal();
        Algorithm algorithm=Algorithm.HMAC256("secret".getBytes());
        String acces_token = JWT.create().withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() +10*60*60*1000))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
        String refresh_token = JWT.create().withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() +30*60*1000))
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
}
