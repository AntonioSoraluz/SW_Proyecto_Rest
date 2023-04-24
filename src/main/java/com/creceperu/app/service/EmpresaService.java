package com.creceperu.app.service;

import java.util.List;
import com.creceperu.app.model.Empresa;

public interface EmpresaService {
	
	List<Empresa> listarEmpresas();
    void guardarEmpresa(Empresa empresa);
    Empresa obtenerEmpresaPorId(long id);
}
