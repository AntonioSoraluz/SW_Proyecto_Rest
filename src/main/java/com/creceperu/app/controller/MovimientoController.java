package com.creceperu.app.controller;

import java.util.Date;
import java.util.List;

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

import com.creceperu.app.controller.dto.MovimientoRegistroDTO;
import com.creceperu.app.model.CuentaBancaria;
import com.creceperu.app.model.Movimiento;
import com.creceperu.app.model.Usuario;
import com.creceperu.app.repository.CuentaBancariaRepository;
import com.creceperu.app.repository.MovimientoRepository;
import com.creceperu.app.repository.UsuarioRepository;
import com.creceperu.app.service.MovimientoService;
import com.creceperu.app.service.UsuarioServiceImpl.CustomUserDetails;

@Controller
@RequestMapping("/registroMovimiento")
public class MovimientoController {
	
	@Autowired
	private MovimientoService movimientoService;
	
	@Autowired
	private CuentaBancariaRepository cuentaBancariaRepository;
	
	@Autowired
	private MovimientoRepository movimientoRepository;
	
	public MovimientoController(MovimientoService movimientoService) {
		super();
		this.movimientoService = movimientoService;
	}
	
	@ModelAttribute("movimiento")
	public MovimientoRegistroDTO retornarNuevoMovimientoDTO() {
		return new MovimientoRegistroDTO();
	}
	
	@GetMapping("/movimientos")
	public String mostrarListaDeMovimientos(Model model, Authentication authentication) {
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		List<Movimiento> movimientos = movimientoRepository.findByObjUsuarioId(customUserDetails.getId());
		model.addAttribute("lstMovimientos", movimientos);
		return "movimientos";
	}
	
	@GetMapping("/deposito")
	public String mostrarFormularioDeRegistroMovimientoDeposito(Model model, Authentication authentication) {
		
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
	    List<CuentaBancaria> cuentasBancarias = cuentaBancariaRepository.findByObjUsuarioId(customUserDetails.getId());
	    model.addAttribute("lstCuentasBancarias", cuentasBancarias);
	    return "movimientoDeposito";
	}
	@GetMapping("/retiro")
	public String mostrarFormularioDeRegistroMovimientoRetiro(Model model, Authentication authentication) {
		
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
	    List<CuentaBancaria> cuentasBancarias = cuentaBancariaRepository.findByObjUsuarioId(customUserDetails.getId());
	    model.addAttribute("lstCuentasBancarias", cuentasBancarias);
	    return "movimientoRetiro";
	}
	
	@PostMapping("/deposito")
	public String registrarMovimientoDeposito(@RequestParam("idUsuario") String idUsuario, @ModelAttribute("movimiento") MovimientoRegistroDTO movimientoRegistroDTO) {
		Long idusuario = Long.parseLong(idUsuario);
		movimientoRegistroDTO.setId(idusuario);
		movimientoRegistroDTO.setTipoMovimiento("Deposito");
		movimientoRegistroDTO.setFechaMovimiento(new Date());
		movimientoService.guardar(movimientoRegistroDTO);
		return "redirect:/registroMovimiento/deposito?exito";
	}
	@PostMapping("/retiro")
	public String registrarMovimientoRetiro(@RequestParam("idUsuario") String idUsuario, @ModelAttribute("movimiento") MovimientoRegistroDTO movimientoRegistroDTO) {
		Long idusuario = Long.parseLong(idUsuario);
		movimientoRegistroDTO.setId(idusuario);
		movimientoRegistroDTO.setTipoMovimiento("Retiro");
		movimientoRegistroDTO.setFechaMovimiento(new Date());
		movimientoService.guardar(movimientoRegistroDTO);
		return "redirect:/registroMovimiento/retiro?exito";
	}
}
