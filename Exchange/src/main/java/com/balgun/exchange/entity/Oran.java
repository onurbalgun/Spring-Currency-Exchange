package com.balgun.exchange.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Oran {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ParaBirimi hesapTur;
    private ParaBirimi hesapTakas;
    private double oran;
}
