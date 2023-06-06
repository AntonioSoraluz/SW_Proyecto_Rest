package com.creceperu.app.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class OportunidadUsuarioId implements Serializable {
	private String oportunidad_id;
	private Long usuario_id;
}
