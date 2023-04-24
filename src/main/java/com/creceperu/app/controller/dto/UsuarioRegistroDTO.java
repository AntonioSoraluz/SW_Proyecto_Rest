package com.creceperu.app.controller.dto;


import java.util.Date;
import lombok.Data;

@Data
public class UsuarioRegistroDTO {

	private Long id;
	private String nombres;
	private String apellidos;
	private String dni;
	private String ubigeo;
	private String ruc;
	private String direccion;
	private String telefono;
	private String email;
	private String emailRecuperacion;
	private String password;
	private Date fechaIngreso;
	private int estado;
	private Collection<Rol> rol;

	public UsuarioRegistroDTO(String nombres, String apellidos, String dni, String ruc, String ubigeo, String direccion, String telefono,
			String email, String emailRecuperacion, String password, Date fechaIngreso, int estado, Collection<Rol> rol) {
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
		this.rol = rol;
	}

	public UsuarioRegistroDTO() {

	}

}
