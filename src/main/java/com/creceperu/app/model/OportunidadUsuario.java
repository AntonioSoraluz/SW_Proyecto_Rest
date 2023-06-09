package com.creceperu.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "oportunidadUsuario")
public class OportunidadUsuario {
	
	@EmbeddedId
	private OportunidadUsuarioId id;
	
	@Column(name = "monto_invertido")
	private double monto_invertido;
	
	@Column(name = "fecha_registro")
	private Date fecha_registro;
	
	@ManyToOne
	@JoinColumn(name = "oportunidad_id", referencedColumnName = "id_oportunidad",insertable = false, updatable = false)
	private Oportunidad objOportunidad;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Usuario objUsuario;

	public OportunidadUsuario(OportunidadUsuarioId id) {
		super();
		this.id = id;
	}
	
	public OportunidadUsuario() {
	}
	
}

