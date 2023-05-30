package com.creceperu.app.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.creceperu.app.model.Rol;
import com.creceperu.app.model.Saldo;
import com.creceperu.app.model.Usuario;
import com.creceperu.app.repository.RolRepository;
import com.creceperu.app.repository.SaldoRepository;
import com.creceperu.app.repository.UsuarioRepository;
import com.creceperu.app.service.UsuarioServiceImpl.CustomUserDetails;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RolRepository rolRepository;
	
	@Autowired
	private SaldoRepository saldoRepository;
	
	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}
	
	@GetMapping("/")
	public String verPaginaDeInicio(Model model, Authentication authentication) {
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		Saldo saldo = saldoRepository.findById(customUserDetails.getId()).orElse(new Saldo(customUserDetails.getId(), 0.0));
		model.addAttribute("saldo", saldo.getSaldo());
		return "principal";
	}
	@GetMapping("/perfilUsuario")
	public String cargarUsuario(@ModelAttribute Usuario usuario, Model model, Authentication authentication) {
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		model.addAttribute("usuario", usuarioRepository.findById(customUserDetails.getId()));
		return "perfilUsuario";
	}
	
	@PostMapping("/perfilUsuario")
	public String actualizarUsuario(@RequestParam("fechaIngreso") String fechaIngreso, 
	    @ModelAttribute Usuario usuario, @RequestParam(value = "rolesSeleccionados", required = false) List<String> rolesSeleccionados, 
	    Model model) throws ParseException {
	    DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date fecha = formatoFecha.parse(fechaIngreso);
	    usuario.setFechaIngreso(fecha);
	    List<Rol> rolesActuales = new ArrayList<>(usuarioRepository.findById(usuario.getId()).get().getRoles());
	    usuarioRepository.save(usuario);
	    if (rolesSeleccionados != null) {
	        List<Rol> nuevosRoles = new ArrayList<>();
	        for (String rol : rolesSeleccionados) {
	            nuevosRoles.add(rolRepository.findByNombre(rol));
	        }
	        usuario.setRoles(nuevosRoles);
	    } else {
	        usuario.setRoles(rolesActuales);
	    }
	    usuarioRepository.save(usuario);
	    Saldo saldo = saldoRepository.findById(usuario.getId()).orElse(null);
	    if (saldo == null) {
	        saldo = new Saldo(usuario, 0.0);
	    }
	    saldoRepository.save(saldo);
	    return "redirect:/perfilUsuario?exito";
	}
	
}
