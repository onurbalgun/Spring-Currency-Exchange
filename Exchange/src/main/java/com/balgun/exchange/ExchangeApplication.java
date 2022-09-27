package com.balgun.exchange;

import com.balgun.exchange.entity.Oran;
import com.balgun.exchange.entity.ParaBirimi;
import com.balgun.exchange.service.OranService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Date;


@SpringBootApplication
public class ExchangeApplication {
    public static void main(String[] args) {

        SpringApplication.run(ExchangeApplication.class, args);
    }
    @Bean
    CommandLineRunner run(OranService oranService){
        return args -> {
            oranService.saveOran(new Oran(null, ParaBirimi.EURO, ParaBirimi.TL,17.73));
            oranService.saveOran(new Oran(null, ParaBirimi.EURO, ParaBirimi.DOLAR,0.96));
            oranService.saveOran(new Oran(null, ParaBirimi.TL, ParaBirimi.EURO,0.056d));
            oranService.saveOran(new Oran(null, ParaBirimi.TL, ParaBirimi.DOLAR,0.054));
            oranService.saveOran(new Oran(null, ParaBirimi.DOLAR, ParaBirimi.EURO,1.04));
            oranService.saveOran(new Oran(null, ParaBirimi.DOLAR, ParaBirimi.TL,18.45));
        };
    }
    }
