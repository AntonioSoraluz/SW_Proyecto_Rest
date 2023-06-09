package com.creceperu.app.model;

import lombok.Data;

@Data
public class OportunidadesTomadasResult {
	private int oportunidadesTomadas;
    private double montoOportunidadesTomadas;

    public OportunidadesTomadasResult(int oportunidadesTomadas, double montoOportunidadesTomadas) {
        this.oportunidadesTomadas = oportunidadesTomadas;
        this.montoOportunidadesTomadas = montoOportunidadesTomadas;
    }
}
