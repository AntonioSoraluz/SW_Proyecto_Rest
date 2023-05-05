package com.creceperu.app.controller.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
public class EmpresaDTO {
	private Integer id_empresa;
	private String ruc;
	private String razon_social;
	private String nombre_comercial;
	private String direc_fiscal;
	private String actividad_economica;
	private Integer telefono;
	private String email;
	private String rep_legal;
	private Date fecha_registro;
	private String estado;
	
	public EmpresaDTO(Integer id_empresa, String ruc, String razon_social, String nombre_comercial, String direc_fiscal,
			String actividad_economica, Integer telefono, String email, String rep_legal, Date fecha_registro,
			String estado) {
		super();
		this.id_empresa = id_empresa;
		this.ruc = ruc;
		this.razon_social = razon_social;
		this.nombre_comercial = nombre_comercial;
		this.direc_fiscal = direc_fiscal;
		this.actividad_economica = actividad_economica;
		this.telefono = telefono;
		this.email = email;
		this.rep_legal = rep_legal;
		this.fecha_registro = fecha_registro;
		this.estado = estado;
	}

	public EmpresaDTO(String ruc, String razon_social, String nombre_comercial, String direc_fiscal,
			String actividad_economica, Integer telefono, String email, String rep_legal, Date fecha_registro,
			String estado) {
		super();
		this.ruc = ruc;
		this.razon_social = razon_social;
		this.nombre_comercial = nombre_comercial;
		this.direc_fiscal = direc_fiscal;
		this.actividad_economica = actividad_economica;
		this.telefono = telefono;
		this.email = email;
		this.rep_legal = rep_legal;
		this.fecha_registro = fecha_registro;
		this.estado = estado;
	}
	
	
}
