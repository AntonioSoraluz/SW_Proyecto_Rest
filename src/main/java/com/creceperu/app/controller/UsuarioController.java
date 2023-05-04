package com.creceperu.app.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.creceperu.app.model.Usuario;
import com.creceperu.app.repository.UsuarioRepository;
import com.creceperu.app.service.UsuarioServiceImpl.CustomUserDetails;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}
	
	@GetMapping("/")
	public String verPaginaDeInicio(Model modelo) {
		return "principal";
	}
	/*@PostMapping("/perfilUsuario")
	public String buscarUsuario(@RequestParam("idPerfil") String idPerfil, @ModelAttribute Usuario usuario, Model model) {
		Long idperfil = Long.parseLong(idPerfil);
		model.addAttribute("usuario", usuarioRepository.findById(idperfil));
		return "perfilUsuario";
	}*/
	@GetMapping("/perfilUsuario")
	public String cargarUsuario(@ModelAttribute Usuario usuario, Model model, Authentication authentication) {
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		model.addAttribute("usuario", usuarioRepository.findById(customUserDetails.getId()));
		return "perfilUsuario";
	}
	
	@PostMapping("/perfilUsuario")
	public String actualizarUsuario(@RequestParam("fechaIngreso") String fechaIngreso, @ModelAttribute Usuario usuario, Model model) throws ParseException {
		DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date fecha = formatoFecha.parse(fechaIngreso);
		usuario.setFechaIngreso(fecha);
		usuarioRepository.save(usuario);
		return "redirect:/perfilUsuario?exito";
	}
	
}
