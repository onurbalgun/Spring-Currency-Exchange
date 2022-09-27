package com.balgun.hesap.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class HesapDetay {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    id;
    private Long hesapId;
    private String details;
    private BigDecimal totalSum;
    private Date date;


}
