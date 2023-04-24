package com.creceperu.app.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
@Entity
@Table(name = "empresas")
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empresa")
	private int id;
	
	@Column(name = "ruc_empresa", length = 11, nullable = false)
	private int ruc;
	
	@Column(name = "razon_social", length = 255, nullable = false)
	private String razon_social;
	
	@Column(name = "direccion_empresa", length = 255, nullable = false)
	private String direccion;
	
	@Column(name = "telefono_empresa", length = 9, nullable = false)
	private int telefono;
	
	@Column(name = "giro_comercial", length = 255, nullable = false)
	private String giro_comercial;
	
	@Column(name = "email_empresa", length = 255, nullable = false)
	private String email;
	
	@Column(name = "calificacion_empresa", length = 3, nullable = false)
	private String calificacion;
	
	@Column(name = "estado_ruc", nullable = false)
	private int estado;
	
	@Column(name = "condicion_ruc", nullable = false)
	private int condicion;
	
	@Temporal(value=TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(name = "fecha_registro", nullable = false)
	private Date fecha_registro;
	
	public String NombreEstado() {
        String nombreEstado = null;
        
        if (estado == 0) nombreEstado= "Activo";
        else if (estado == 1) nombreEstado =  "Suspensión Temporal";
        else if (estado == 2) nombreEstado =  "Baja Provisional";
        else if (estado == 3) nombreEstado =  "Baja Definitiva";
        else if (estado == 4) nombreEstado =  "Baja Provisional de Oficio";
        else if (estado == 5) nombreEstado =  "Baja Definitiva de Oficio";
        else nombreEstado =  "Otros";
        return nombreEstado;
    }
	
	public String NombreCondicion() {
        String nombreCondicion = null;

        if (condicion == 0) nombreCondicion= "Habido";
        else if (condicion == 1) nombreCondicion =  "No Habido";
        else nombreCondicion =  "Otros";
        return nombreCondicion;
    }
}
