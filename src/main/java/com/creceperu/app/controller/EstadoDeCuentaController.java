package com.creceperu.app.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creceperu.app.model.CuentaBancaria;
import com.creceperu.app.model.OportunidadUsuario;
import com.creceperu.app.model.OportunidadesUsuarioResult;
import com.creceperu.app.model.Saldo;
import com.creceperu.app.repository.CuentaBancariaRepository;
import com.creceperu.app.repository.OportunidadUsuarioRepository;
import com.creceperu.app.repository.SaldoRepository;
import com.creceperu.app.service.UsuarioServiceImpl.CustomUserDetails;

@Controller
@RequestMapping("/estadoDeCuenta")
public class EstadoDeCuentaController {
	
	@Autowired
	private SaldoRepository saldoRepository;
	
	@Autowired
	private CuentaBancariaRepository cuentaBancariaRepository;
	
	@Autowired
	private OportunidadUsuarioRepository oportunidadUsuarioRepository;
	
	@GetMapping
	public String verEstadoDeCuenta(Model model, Authentication authentication) {
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		Saldo saldo = saldoRepository.findById(customUserDetails.getId()).orElse(new Saldo(customUserDetails.getId(), 0.0));
		model.addAttribute("saldo", saldo.getSaldo());
		List<CuentaBancaria> cuentasBancarias = cuentaBancariaRepository.findByObjUsuarioId(customUserDetails.getId());
		model.addAttribute("cuentasBancarias", cuentasBancarias);
		List<OportunidadUsuario> oportunidades = oportunidadUsuarioRepository.findByObjUsuarioId(customUserDetails.getId());
		model.addAttribute("oportunidades", oportunidades);
		List<Object[]> results = oportunidadUsuarioRepository.getOportunidadesUsuario(customUserDetails.getId());
		List<OportunidadesUsuarioResult> oportunidadesUsuario = new ArrayList<>();

		for (Object[] result : results) {
		    int count = ((BigInteger) result[0]).intValue();
		    Double sum = ((Number) result[1]).doubleValue();
		    OportunidadesUsuarioResult oportunidadU = new OportunidadesUsuarioResult(count, sum);
		    oportunidadesUsuario.add(oportunidadU);
		}
		model.addAttribute("oportunidadesUsuario", oportunidadesUsuario);
		return "estadoCuenta";
	}
}
