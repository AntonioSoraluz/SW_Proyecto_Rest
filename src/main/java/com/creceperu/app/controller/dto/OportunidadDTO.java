package com.creceperu.app.controller.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.creceperu.app.model.Factura;
import com.creceperu.app.model.Usuario;

import lombok.Data;

@Data
public class OportunidadDTO {
	
	private Integer id_oportunidad;
	private String calificacion;
	private double rendimiento;
	private int total_partes;
	private int partes;
	private double monto;
	private Date fechaPago;
	private Date fecharegistro;
	private Integer id_empresa;
	private Set<Factura> facturas = new HashSet<>();
	private Set<Usuario> usuario = new HashSet<>();
	
	public OportunidadDTO(Integer id_oportunidad, String calificacion, double rendimiento, int total_partes, int partes,
			double monto, Date fechaPago, Date fecharegistro, Integer id_empresa, Set<Factura> facturas, Set<Usuario> usuario) {
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
		this.facturas = facturas;
		this.usuario = usuario;
	}
	
	public OportunidadDTO(String calificacion, double rendimiento, int total_partes, int partes, double monto,
			Date fechaPago, Date fecharegistro, Integer id_empresa, Set<Factura> facturas, Set<Usuario> usuario) {
		super();
		this.calificacion = calificacion;
		this.rendimiento = rendimiento;
		this.total_partes = total_partes;
		this.partes = partes;
		this.monto = monto;
		this.fechaPago = fechaPago;
		this.fecharegistro = fecharegistro;
		this.id_empresa = id_empresa;
		this.facturas = facturas;
		this.usuario = usuario;
	}
	
	public OportunidadDTO() {
	}
	
}

