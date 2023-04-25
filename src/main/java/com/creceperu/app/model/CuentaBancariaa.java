package com.creceperu.app.model;

import java.util.Collection;
import java.util.Date;
import javax.persistence.*;

import lombok.Data;
    @Data
    @Entity
    @Table(name = "cuenta_bancaria")
    public class CuentaBancariaa {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @ManyToOne
        @JoinColumn(name = "id_usuario")
        private Usuario cuentaUsuario;

        @Column(name = "id_banco")
        private String banco;

        @Column(name = "id_tipo_cuenta_bancaria")
        private String tipoCuentaBancaria;

        @Column(name = "id_moneda")
        private String moneda;

        @Column(name = "numero_cuenta")
        private String numeroCuenta;


    }


