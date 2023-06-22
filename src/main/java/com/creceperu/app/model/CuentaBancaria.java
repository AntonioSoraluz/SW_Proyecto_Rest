package com.creceperu.app.model;

import java.util.Date;

import lombok.Data;

@Data
public class CuentaBancaria {
	private Integer id_cuentaBancaria;
	private String numeroCuenta;
	private Double monto;
	private String nombre_banco;
	private Integer userid;
	private Date fechaRegistro;

}
