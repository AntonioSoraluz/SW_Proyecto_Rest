package com.creceperu.app.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tipoCuentaBancaria")
public class TipoCuentaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipoCuentaBancaria")
    private Long idTipoCuentaBancaria;

    @Column(name = "nombreTipoCuentaBancaria")
    private String nombreTipoCuentaBancaria;

}