package com.creceperu.app.controller.dto;

import java.util.Date;
import lombok.Data;

@Data
public class EmpresaRegistroDTO {
		
		private int id;
		private String ruc;
		private String razon_social;
		private String direccion;
		private String telefono;
		private String giro_comercial;
		private String email;
		private String calificacion;
		private int estado;
		private int condicion;
		private Date fecha_registro;

		public EmpresaRegistroDTO(String ruc, String razon_social, String direccion, String telefono, String giro_comercial, String email, String calificacion, int estado,
				int condicion, Date fecha_registro) {
			super();
			this.ruc = ruc;
			this.razon_social = razon_social;
			this.direccion = direccion;
			this.telefono = telefono;
			this.giro_comercial = giro_comercial;
			this.email = email;
			this.calificacion = calificacion;
			this.estado = estado;
			this.condicion = condicion;
			this.fecha_registro = fecha_registro;
		}
		
		public EmpresaRegistroDTO() {
			
		}
}

