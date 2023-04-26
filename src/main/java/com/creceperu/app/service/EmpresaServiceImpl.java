package com.creceperu.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.creceperu.app.controller.dto.EmpresaRegistroDTO;
import com.creceperu.app.model.Empresa;
import com.creceperu.app.repository.EmpresaRepository;

@Service
public class EmpresaServiceImpl {
	
	@Autowired
	private EmpresaRepository empresaRepository;

	public EmpresaServiceImpl(EmpresaRepository empresaRepository) {
		super();
		this.empresaRepository = empresaRepository;
	}
	
	public List<Empresa> listarEmpresas() {
		return empresaRepository.findAll();
	}

	public Empresa guardarEmpresa(EmpresaRegistroDTO registroEmpresaDTO) {
		return null;
		
	}
	
	public Empresa actualizarEmpresa(EmpresaRegistroDTO registroEmpresaDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Empresa obtenerEmpresaPorId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void init() {
		// TODO Auto-generated method stub
		
	}

}
