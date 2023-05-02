package com.creceperu.app.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.creceperu.app.controller.dto.CuentaBancariaRegistroDTO;
import com.creceperu.app.repository.BancoRepository;
import com.creceperu.app.service.CuentaBancariaService;
import com.creceperu.app.service.UsuarioServiceImpl.CustomUserDetails;

@Controller
@RequestMapping("/registroCuentaBancaria")
public class RegistroCuentaBancariaController {
	
	private CuentaBancariaService cuentaBancariaService;
	
	/*private CustomUserDetails usuarioTemporal;*/
	
	@Autowired
	private BancoRepository bancoRepository;
	
	public RegistroCuentaBancariaController(CuentaBancariaService cuentaBancariaService) {
		super();
		this.cuentaBancariaService = cuentaBancariaService;
	}
	
	@ModelAttribute("cuentaBancaria")
	public CuentaBancariaRegistroDTO retornarNuevoCuentaBancariaDTO() {
		return new CuentaBancariaRegistroDTO();
	}
	
	@GetMapping
	public String mostrarFormularioDeRegistroCuentaBancaria(Model model) {
		model.addAttribute("lstBanco", bancoRepository.findAll());
		return "cuentaBancaria";
	}
	
	@PostMapping
	public String registrarCuentaBancaria(@RequestParam("idUsuario") String idUsuario, @ModelAttribute("cuentaBancaria") CuentaBancariaRegistroDTO cuentaBancariaDTO) {
		Long idusuario = Long.parseLong(idUsuario);
		cuentaBancariaDTO.setId(idusuario);
		cuentaBancariaDTO.setFechaRegistro(new Date());
		cuentaBancariaService.guardar(cuentaBancariaDTO);
		return "redirect:/registroCuentaBancaria?exito";
	}
}
