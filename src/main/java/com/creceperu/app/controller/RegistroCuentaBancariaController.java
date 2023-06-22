package com.creceperu.app.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.creceperu.app.model.Banco;
import com.creceperu.app.model.CuentaBancaria;
import com.creceperu.app.service.UsuarioServiceImpl.CustomUserDetails;

import lombok.extern.apachecommons.CommonsLog;

@Controller
@RequestMapping("/cuentaBancaria")
@CommonsLog
public class RegistroCuentaBancariaController {
	
	//PostgreSQL
	String URL_LISTCUENTABANCARIA = "http://localhost:8091/rest/cuentaBancaria/porUser";
	
	//PostgreSQL
	String URL_CUENTABANCARIA = "http://localhost:8091/rest/cuentaBancaria";
		
	//PostgreSQL
	String URL_VALIDARCUENTABANCARIA = "http://localhost:8091/rest/cuentaBancaria/porNumero";
		
	//MongoDB
	String URL_BANCO = "http://localhost:8093/rest/banco";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@ModelAttribute("cuentaBancaria")
	public CuentaBancaria retornarNuevaCuentaBancaria() {
		return new CuentaBancaria();
	}
	
	/*@GetMapping("/CuentasBancarias")
	public String verEstadoDeCuenta(Model model, Authentication authentication, @RequestParam(defaultValue = "0") int page) {
		int pageSize = 4; // Número de elementos por página
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		Sort sort = Sort.by(Sort.Direction.DESC, "fechaRegistro");
		Pageable pageable = PageRequest.of(page, pageSize, sort);
		ResponseEntity<List<CuentaBancaria>> responseEntity = restTemplate.exchange(
	            URL_LISTCUENTABANCARIA + "/" + customUserDetails.getId().intValue(),
	            HttpMethod.GET,
	            null,
	            new ParameterizedTypeReference<List<CuentaBancaria>>() {}
	    );
		List<CuentaBancaria> lstCuentaBancaria = responseEntity.getBody();
		
		model.addAttribute("cuentasBancarias", lstCuentaBancaria.getContent());
		model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", lstCuentaBancaria.getTotalPages());
		return "misCuentasBancarias";
	}*/
	
	@GetMapping("/CuentasBancarias")
	public String verEstadoDeCuenta(Model model, Authentication authentication, @RequestParam(defaultValue = "0") int page) {
	    int pageSize = 4; // Número de elementos por página
	    CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
	    Sort sort = Sort.by(Sort.Direction.DESC, "fechaRegistro");
	    Pageable pageable = PageRequest.of(page, pageSize, sort);

	    ResponseEntity<List<CuentaBancaria>> responseEntity = restTemplate.exchange(
	            URL_LISTCUENTABANCARIA + "/" + customUserDetails.getId().intValue(),
	            HttpMethod.GET,
	            null,
	            new ParameterizedTypeReference<List<CuentaBancaria>>() {}
	    );
	    List<CuentaBancaria> lstCuentaBancaria = responseEntity.getBody();

	    int start = (int) pageable.getOffset();
	    int end = Math.min((start + pageable.getPageSize()), lstCuentaBancaria.size());
	    List<CuentaBancaria> sublist = lstCuentaBancaria.subList(start, end);

	    Page<CuentaBancaria> cuentaBancariaPage = new PageImpl<>(sublist, pageable, lstCuentaBancaria.size());

	    model.addAttribute("cuentasBancarias", cuentaBancariaPage.getContent());
	    model.addAttribute("currentPage", cuentaBancariaPage.getNumber());
	    model.addAttribute("totalPages", cuentaBancariaPage.getTotalPages());
	    return "misCuentasBancarias";
	}

	
	@GetMapping("/registroCuentaBancaria")
	public String mostrarFormularioDeRegistroCuentaBancaria(Model model) {
		ResponseEntity<List<Banco>> responseEntity = restTemplate.exchange(URL_BANCO, HttpMethod.GET, null, new ParameterizedTypeReference<List<Banco>>(){});
		List<Banco> lstBanco = responseEntity.getBody();
		model.addAttribute("lstBanco", lstBanco);
		return "cuentaBancaria";
	}
	
	@PostMapping("/registroCuentaBancaria")
	public String registrarCuentaBancaria(Model model, Authentication authentication, @ModelAttribute("cuentaBancaria") CuentaBancaria objCuentaBancaria) {
		/*if(cuentaBancariaRepository.existsByNumeroCuenta(cuentaBancariaDTO.getNumeroCuenta())) {
	        return "redirect:/registroCuentaBancaria?error";
	    }*/
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		objCuentaBancaria.setUserid(customUserDetails.getId().intValue());
		objCuentaBancaria.setFechaRegistro(new Date());
		HttpEntity<CuentaBancaria> request = new HttpEntity<CuentaBancaria>(objCuentaBancaria);
		restTemplate.postForObject(URL_CUENTABANCARIA, request, CuentaBancaria.class);
		return "redirect:/cuentaBancaria/registroCuentaBancaria?exito";
	}
	/*
	@PostMapping("/registroCuentaBancaria")
	public String registrarCuentaBancaria(Model model, @RequestParam("idUsuario") String idUsuario, @ModelAttribute("cuentaBancaria") CuentaBancariaRegistroDTO cuentaBancariaDTO) {
		if(cuentaBancariaRepository.existsByNumeroCuenta(cuentaBancariaDTO.getNumeroCuenta())) {
	        return "redirect:/cuentaBancaria/registroCuentaBancaria?error";
	    }
		Long idusuario = Long.parseLong(idUsuario);
		cuentaBancariaDTO.setId(idusuario);
		cuentaBancariaDTO.setFechaRegistro(new Date());
		cuentaBancariaService.guardar(cuentaBancariaDTO);
		return "redirect:/cuentaBancaria/registroCuentaBancaria?exito";
	}*/
}
