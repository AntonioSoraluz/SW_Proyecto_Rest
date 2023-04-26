package com.creceperu.app.model;

import lombok.Data;

import javax.persistence.*;
@Entity
@Data
@Table(name = "tipoMoneda")
public class TipoMoneda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipoMoneda")
    private Long idTipoMoneda;

    @Column(name = "nombreTipoMoneda")
    private String nombreTipoMoneda;
}