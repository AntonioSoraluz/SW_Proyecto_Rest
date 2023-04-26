package com.creceperu.app.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.creceperu.app.controller.dto.EmpresaRegistroDTO;
import com.creceperu.app.model.Empresa;

public interface EmpresaService extends UserDetailsService{
	
	public List<Empresa> listarEmpresas();
	public Empresa guardarEmpresa(EmpresaRegistroDTO registroEmpresaDTO);
	public Empresa actualizarEmpresa(EmpresaRegistroDTO registroEmpresaDTO);
	public Empresa obtenerEmpresaPorId(long id);
	public void init();
}
