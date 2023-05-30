package com.creceperu.app.controller.dto;

import java.util.Date;

import lombok.Data;

@Data
public class OportunidadDTO {
	
	private String id_oportunidad;
	private String calificacion;
	private double rendimiento;
	private int total_partes;
	private int partes;
	private double monto;
	private Date fechaPago;
	private Date fecharegistro;
	private Integer id_empresa;
	private String estado;
	
	public OportunidadDTO(String id_oportunidad, String calificacion, double rendimiento, int total_partes, int partes,
			double monto, Date fechaPago, Date fecharegistro, Integer id_empresa, String estado) {
		super();
		this.id_oportunidad = id_oportunidad;
		this.calificacion = calificacion;
		this.rendimiento = rendimiento;
		this.total_partes = total_partes;
		this.partes = partes;
		this.monto = monto;
		this.fechaPago = fechaPago;
		this.fecharegistro = fecharegistro;
		this.id_empresa = id_empresa;
		this.estado = estado;
	}
	
	public OportunidadDTO(String calificacion, double rendimiento, int total_partes, int partes, double monto,
			Date fechaPago, Date fecharegistro, Integer id_empresa, String estado) {
		super();
		this.calificacion = calificacion;
		this.rendimiento = rendimiento;
		this.total_partes = total_partes;
		this.partes = partes;
		this.monto = monto;
		this.fechaPago = fechaPago;
		this.fecharegistro = fecharegistro;
		this.id_empresa = id_empresa;
		this.estado = estado;
	}
	
	public OportunidadDTO() {
	}
	
}

