package com.creceperu.app.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "capitalistas")
public class Capitalista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_capitalista;
	
	@Column(name = "nro_documento")
	private String documento;
	
	@Column(name = "nom_capitalista")
	private String nombre;
	
	@Column(name = "direccion_capitalista")
	private String direccion;
	
	@Column(name = "telefono_capitalista")
	private String telefono;
	
	@Column(name = "email_capitalista")
	private String email;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_registro;
}
