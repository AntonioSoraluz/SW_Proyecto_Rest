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
    @Column(name = "id_Cuenta_Bancaria")
    private Long id_Cuenta_Bancaria;

    @ManyToOne
    @JoinColumn(name = "id_banco")
    private TipoBanco id_banco;

    @ManyToOne
    @JoinColumn(name = "id_tipo_cuenta_bancaria")
    private TipoCuentaBancaria id_tipo_cuenta_bancaria;

    @ManyToOne
    @JoinColumn(name = "id_moneda")
    private TipoMoneda id_moneda;

    @Column(name = "numero_cuenta")
    private String numero_cuenta;

    @Column(name = "cci_CuentaBancaria")
    private String cci_CuentaBancaria;
}
