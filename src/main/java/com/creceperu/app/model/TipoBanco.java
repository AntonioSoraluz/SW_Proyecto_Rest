//package com.creceperu.app.model;
//import javax.persistence.*;
//import lombok.Data;
//import org.hibernate.mapping.List;
//
//
//@Data
//@Entity
//@Table(name = "TipoBanco")
//public class TipoBanco {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "idTipoBanco")
//    private Long idTipoBanco;
//
//    @Column(name = "nombre_tipo_banco")
//    private String nombreTipoBanco;
//
//    @OneToMany(mappedBy = "TipoBanco")
//    private CuentaBancariaa cuentaBancaria;
//}
