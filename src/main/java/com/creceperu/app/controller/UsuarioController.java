package com.creceperu.app.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.creceperu.app.model.Usuario;
import com.creceperu.app.repository.UsuarioRepository;
import com.creceperu.app.service.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService servicio;
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
	@PostMapping("/perfilUsuario")
	public String buscarUsuario(@RequestParam("idPerfil") String idPerfil, @ModelAttribute Usuario usuario, Model model) {
		Long idperfil = Long.parseLong(idPerfil);
		model.addAttribute("usuario", usuarioRepository.findById(idperfil));
		return "perfilUsuario";
	}
	@PostMapping("/actualizarUsuario")
	public String actualizarUsuario(@RequestParam("fechaIngreso") String fechaIngreso, @ModelAttribute Usuario usuario, Model model) throws ParseException {
		DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date fecha = formatoFecha.parse(fechaIngreso);
		usuario.setFechaIngreso(fecha);
		usuario.setRuc(null);
		usuarioRepository.save(usuario);
		return "perfilUsuario";
	}
	
}
