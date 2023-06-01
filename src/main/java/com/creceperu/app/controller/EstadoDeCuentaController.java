package com.creceperu.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creceperu.app.model.CuentaBancaria;
import com.creceperu.app.model.Saldo;
import com.creceperu.app.repository.CuentaBancariaRepository;
import com.creceperu.app.repository.SaldoRepository;
import com.creceperu.app.service.UsuarioServiceImpl.CustomUserDetails;

@Controller
@RequestMapping("/estadoDeCuenta")
public class EstadoDeCuentaController {
	
	@Autowired
	private SaldoRepository saldoRepository;
	
	@Autowired
	private CuentaBancariaRepository cuentaBancariaRepository;
	
	@GetMapping
	public String verEstadoDeCuenta(Model model, Authentication authentication) {
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		Saldo saldo = saldoRepository.findById(customUserDetails.getId()).orElse(new Saldo(customUserDetails.getId(), 0.0));
		model.addAttribute("saldo", saldo.getSaldo());
		List<CuentaBancaria> cuentasBancarias = cuentaBancariaRepository.findByObjUsuarioId(customUserDetails.getId());
		model.addAttribute("cuentasBancarias", cuentasBancarias);
		return "estadoCuenta";
	}
}
