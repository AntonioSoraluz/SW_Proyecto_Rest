package com.creceperu.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Depositos {

	
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	
	@Column(name = "numero_cuenta")
	 private int numeroCuenta;
	@Column(name = "saldo_actual")
	 private double saldoActual;
	 
	 


	public Depositos() {
		super();
		// TODO Auto-generated constructor stub
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
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
