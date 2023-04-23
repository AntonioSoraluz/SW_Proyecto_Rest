package com.creceperu.app.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
@Entity
@Table(name = "capitalista_tipos")
public class TipoCapitalista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_tipocapitalista;
	
	@Column(name = "tipo_capitalista")
	private String tipo;
	
	private int estado;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private Date fecha_registro;
}
