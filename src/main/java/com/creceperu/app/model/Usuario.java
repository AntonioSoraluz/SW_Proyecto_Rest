package com.creceperu.app.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombres")
	private String nombres;

	@Column(name = "apellidos")
	private String apellidos;

	@Column(name = "dni")
	private String dni;

	@Column(name = "ubigeo")
	private String ubigeo;
	
	@Column(name = "ruc")
	private String ruc;

	@Column(name = "direccion")
	private String direccion;

	@Column(name = "telefono")
	private String telefono;

	private String email;

	@Column(name = "emailRecuperacion")
	private String emailRecuperacion;

	private String password;
	
	@Column(name = "fechaIngreso")
	private Date fechaIngreso;
	
	@Column(name = "estado")
	private int estado;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id"))
	private Collection<Rol> roles;

	public Usuario(Long id, String nombres, String apellidos, String dni, String ubigeo, String ruc, String direccion, String telefono,
			String email, String emailRecuperacion, String password, Date fechaIngreso, int estado, Collection<Rol> roles) {
		super();
		this.id = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.dni = dni;
		this.ubigeo = ubigeo;
		this.ruc = ruc;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.emailRecuperacion = emailRecuperacion;
		this.password = password;
		this.fechaIngreso = fechaIngreso;
		this.estado = estado;
		this.roles = roles;
	}

	public Usuario(String nombres, String apellidos, String dni, String ubigeo, String ruc, String direccion, String telefono, String email,
			String emailRecuperacion, String password, Date fechaIngreso, int estado, Collection<Rol> roles) {
		super();
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.dni = dni;
		this.ubigeo = ubigeo;
		this.ruc = ruc;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.emailRecuperacion = emailRecuperacion;
		this.password = password;
		this.fechaIngreso = fechaIngreso;
		this.estado = estado;
		this.roles = roles;
	}

	public Usuario() {

	}

}
