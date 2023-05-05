package com.creceperu.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table( name = "saldo")
public class Saldo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_saldo;
	
	@Column(name = "saldo")
	private Double saldo;
	
	private Long id;
	@ManyToOne
	@JoinColumn(name = "id", insertable = false, updatable = false)
	private Usuario objUsuario;
	
	
}
