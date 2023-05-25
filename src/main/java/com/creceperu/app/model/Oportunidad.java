package com.creceperu.app.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "oportunidad")
public class Oportunidad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_oportunidad;
	
	@Column(name = "calificacion")
	private String calificacion;
	
	@Column(name = "rendimiento")
	private double rendimiento;
	
	@Column(name = "total_partes")
	private int total_partes;
	
	@Column(name = "partes")
	private int partes;
	
	@Column(name = "monto")
	private double monto;
	
	@Column(name = "fechaPago")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd ")
	private Date fechaPago;
	
	@Column(name = "fecharegistro")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fecharegistro;
	
	private Integer id_empresa;
	@ManyToOne
	@JoinColumn(name = "id_empresa", insertable = false, updatable = false)
	private Empresa objEmpresa;
	
	@ManyToMany(mappedBy = "oportunidades")
	private Set<Factura> facturas = new HashSet<>();
	
	@ManyToMany(mappedBy = "oportunidad")
	private Set<Usuario> usuario = new HashSet<>();

	public Oportunidad(Integer id_oportunidad, String calificacion, double rendimiento, int total_partes, int partes,
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

	public Oportunidad(String calificacion, double rendimiento, int total_partes, int partes, double monto,
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

	public Oportunidad() {
	}
	
}
