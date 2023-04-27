//package com.creceperu.app.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import com.creceperu.app.controller.dto.EmpresaRegistroDTO;
//import com.creceperu.app.service.EmpresaService;
//
//@Controller
//@RequestMapping("/registroEmpresa")
//public class RegistroEmpresaController {
//
//	private EmpresaService empresaService;
//
//	public RegistroEmpresaController(EmpresaService empresaService) {
//		super();
//		this.empresaService = empresaService;
//	}
//
//	@ModelAttribute("empresa")
//	public EmpresaRegistroDTO retornarNuevaEmpresaRegistroDTO() {
//		return new EmpresaRegistroDTO();
//	}
//
//	@GetMapping
//	public String mostrarFormularioDeRegistro() {
//		return "registroEmpresa";
//	}
//}
