package com.creceperu.app.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creceperu.app.controller.dto.UsuarioRegistroDTO;
import com.creceperu.app.model.Rol;
import com.creceperu.app.model.Usuario;
import com.creceperu.app.service.UsuarioService;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioController {

	private UsuarioService usuarioService;

	public RegistroUsuarioController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}
	
	@ModelAttribute("usuario")
	public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO() {
		return new UsuarioRegistroDTO();
	}

	@GetMapping
	public String mostrarFormularioDeRegistro() {
		return "registro";
	}
	
	@PostMapping
	public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO) {
		registroDTO.setRuc(registroDTO.getRuc());
		registroDTO.setFechaIngreso(new Date());
		registroDTO.setEstado(1);
		registroDTO.setRol(registroDTO.getRol());
		usuarioService.guardar(registroDTO);
		return "redirect:/registro?exito";
	}
}
