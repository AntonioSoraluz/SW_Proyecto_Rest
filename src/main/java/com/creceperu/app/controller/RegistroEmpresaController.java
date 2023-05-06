package com.creceperu.app.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creceperu.app.controller.dto.EmpresaDTO;
import com.creceperu.app.service.EmpresaService;

@Controller
@RequestMapping("/registroEmpresa")
public class RegistroEmpresaController {
	
	@Autowired
	private EmpresaService empresaService;
	
	public RegistroEmpresaController(EmpresaService empresaService) {
		super();
		this.empresaService = empresaService;
	}
	
	@ModelAttribute("empresa")
	public EmpresaDTO retornarNuevoEmpresaDTO() {
		return new EmpresaDTO();
	}
	
	@GetMapping
	public String mostrarFormularioDeRegistroEmpresa(Model model) {	
		return "empresa";
	}
	
	@PostMapping
	public String registrarEmpresa(@ModelAttribute("empresa") EmpresaDTO registroEmpresaDTO) {
		registroEmpresaDTO.setFecha_registro(new Date());
		registroEmpresaDTO.setEstado("Activo");
		empresaService.guardar(registroEmpresaDTO);
		return "redirect:/registroEmpresa?exito";
	}	

}
