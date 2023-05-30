package com.creceperu.app.model;

import javax.persistence.Column;
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
	
	@Id
	@Column(name = "oportunidad_id")
	private String oportunidad_id;
	
	@ManyToOne
	@JoinColumn(name = "oportunidad_id", referencedColumnName = "id_oportunidad",insertable = false, updatable = false)
	private Oportunidad objOportunidad;
	
	@Column(name = "usuario_id")
	private Long usuario_id;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Usuario objUsuario;

	public OportunidadUsuario(String oportunidad_id, Long usuario_id) {
		super();
		this.oportunidad_id = oportunidad_id;
		this.usuario_id = usuario_id;
	}
	
	public OportunidadUsuario() {
	}
	
}

