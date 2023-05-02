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
import com.creceperu.app.model.Usuario;
import com.creceperu.app.repository.CuentaBancariaRepository;
import com.creceperu.app.repository.UsuarioRepository;
import com.creceperu.app.service.MovimientoService;
import com.creceperu.app.service.UsuarioServiceImpl.CustomUserDetails;

@Controller
@RequestMapping("/registroMovimientoDeposito")
public class RegistroMovimientoController {
	
	private MovimientoService movimientoService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CuentaBancariaRepository cuentaBancariaRepository;
	
	public RegistroMovimientoController(MovimientoService movimientoService) {
		super();
		this.movimientoService = movimientoService;
	}
	
	@ModelAttribute("movimiento")
	public MovimientoRegistroDTO retornarNuevoMovimientoDTO() {
		return new MovimientoRegistroDTO();
	}
	
	/*@GetMapping
	public String mostrarFormularioDeRegistroMovimiento(Model model) {
		model.addAttribute("lstCuentasBancarias", cuentaBancariaRepository.findAll());
		return "movimiento";
	}*/
	@GetMapping
	public String mostrarFormularioDeRegistroMovimientoDeposito(Model model, Authentication authentication) {
		
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		
	    // buscar todas las cuentas bancarias del usuario
	    List<CuentaBancaria> cuentasBancarias = cuentaBancariaRepository.findByObjUsuarioId(customUserDetails.getId());

	    // agregar la lista de cuentas bancarias al modelo
	    model.addAttribute("lstCuentasBancarias", cuentasBancarias);
	    
	    return "movimiento";
	}
	
	@PostMapping
	public String registrarMovimiento(@RequestParam("idUsuario") String idUsuario, @ModelAttribute("movimiento") MovimientoRegistroDTO movimientoRegistroDTO) {
		Long idusuario = Long.parseLong(idUsuario);
		movimientoRegistroDTO.setId(idusuario);
		movimientoRegistroDTO.setTipoMovimiento("Deposito");
		movimientoRegistroDTO.setFechaMovimiento(new Date());
		movimientoService.guardar(movimientoRegistroDTO);
		return "redirect:/registroMovimientoDeposito?exito";
	}
}
