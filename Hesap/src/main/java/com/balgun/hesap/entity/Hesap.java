package com.balgun.hesap.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Hesap")
public class Hesap {
    @Id   @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hesapid")
    private Long    id;
    @Column(name = "userid")
    private Long userId;
    @Column(name = "hesaptur")
    private ParaBirimi hesapTur;
    @Column(name = "hesapno")
    private String hesapNo;
    @Column(name = "hesapbakiye")
    private BigDecimal bakiye;



//    private Long  version;

    //  @ManyToOne
    //  @JoinColumn(name = "kullanici_hesap_fk_id")
    //  private User kullanici_hesap_fk;


}
