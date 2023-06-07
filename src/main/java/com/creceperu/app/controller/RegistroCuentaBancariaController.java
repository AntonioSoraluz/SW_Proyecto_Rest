package com.creceperu.app.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.creceperu.app.controller.dto.CuentaBancariaRegistroDTO;
import com.creceperu.app.repository.BancoRepository;
import com.creceperu.app.repository.CuentaBancariaRepository;
import com.creceperu.app.service.CuentaBancariaService;

@Controller
@RequestMapping("/registroCuentaBancaria")
public class RegistroCuentaBancariaController {
	
	@Autowired
	private CuentaBancariaService cuentaBancariaService;
	
	@Autowired
	private BancoRepository bancoRepository;
	
	@Autowired
	private CuentaBancariaRepository cuentaBancariaRepository;
	
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
	public String registrarCuentaBancaria(Model model, @RequestParam("idUsuario") String idUsuario, @ModelAttribute("cuentaBancaria") CuentaBancariaRegistroDTO cuentaBancariaDTO) {
		if(cuentaBancariaRepository.existsByNumeroCuenta(cuentaBancariaDTO.getNumeroCuenta())) {
	        return "redirect:/registroCuentaBancaria?error";
	    }
		Long idusuario = Long.parseLong(idUsuario);
		cuentaBancariaDTO.setId(idusuario);
		cuentaBancariaDTO.setFechaRegistro(new Date());
		cuentaBancariaService.guardar(cuentaBancariaDTO);
		return "redirect:/registroCuentaBancaria?exito";
	}
}
