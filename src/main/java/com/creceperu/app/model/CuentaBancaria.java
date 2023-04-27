package com.creceperu.app.model;

import javax.persistence.Column;

public class CuentaBancaria {

	
	@Column(name = "numero_cuenta")
	 private int numeroCuenta;
	@Column(name = "saldo_actual")
	 private double saldoActual;
	 
	 


	public CuentaBancaria() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getNumeroCuenta() {
		return numeroCuenta;
	}



	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}



	public double getSaldoActual() {
		return saldoActual;
	}



	public void setSaldoActual(double saldoActual) {
		this.saldoActual = saldoActual;
	}

	 
	 
	 
}
