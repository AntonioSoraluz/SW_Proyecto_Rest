package com.creceperu.app.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.creceperu.app.model.Empresa;
import com.creceperu.app.model.Oportunidad;
import com.creceperu.app.model.OportunidadesDisponiblesResult;
import com.creceperu.app.model.OportunidadesPagadasResult;
import com.creceperu.app.model.OportunidadesTomadasResult;
import com.creceperu.app.model.Rol;
import com.creceperu.app.model.Saldo;
import com.creceperu.app.model.Usuario;
import com.creceperu.app.repository.EmpresaRepository;
import com.creceperu.app.repository.OportunidadRepository;
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
	
	@Autowired
	private OportunidadRepository oportunidadRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}
	
	@GetMapping("/")
	public String verPaginaDeInicio(Model model, Authentication authentication) {
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		Saldo saldo = saldoRepository.findById(customUserDetails.getId()).orElse(new Saldo(customUserDetails.getId(), 0.0));
		model.addAttribute("saldo", saldo.getSaldo());
		List<Oportunidad> oportunidades = oportunidadRepository.findAllOportunidades();
		model.addAttribute("lstOportunidades", oportunidades);
		return "principal";
	}
	@PostMapping("/filtrarOportunidad")
	public String verPaginaConFiltro(Model model, Authentication authentication, @RequestParam("filtro") String filtro) {
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		Saldo saldo = saldoRepository.findById(customUserDetails.getId()).orElse(new Saldo(customUserDetails.getId(), 0.0));
		model.addAttribute("saldo", saldo.getSaldo());
		List<Oportunidad> oportunidades = oportunidadRepository.findOportunidadesXFiltro(filtro);
		model.addAttribute("lstOportunidades", oportunidades);
		return "principal";
	}
	@ResponseBody
	public String verSaldo(Model model, Authentication authentication) {
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		Saldo saldo = saldoRepository.findById(customUserDetails.getId()).orElse(new Saldo(customUserDetails.getId(), 0.0));
		model.addAttribute("saldo", saldo.getSaldo());
		return "layout";
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
	
	@GetMapping("/verOportunidad")
	public String verOportunidadDetalle(@RequestParam("idOportunidad") String idOportunidad, 
			@RequestParam("razonsocial") String razonsocial,Model model, Authentication authentication) {
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		Saldo saldo = saldoRepository.findById(customUserDetails.getId()).orElse(new Saldo(customUserDetails.getId(), 0.0));
		model.addAttribute("saldo", saldo.getSaldo());
		Oportunidad oportunidad = oportunidadRepository.findByOportunidadId(idOportunidad);
		model.addAttribute("oportunidadSelecionada", oportunidad);
		Empresa empresa = empresaRepository.findByRazonsocial(razonsocial);
		model.addAttribute("empresaSeleccionada", empresa);
		List<Object[]> results = oportunidadRepository.getOportunidadesDisponibles(razonsocial);
		List<OportunidadesDisponiblesResult> oportunidadesDisponibles = new ArrayList<>();

		for (Object[] result : results) {
		    int count = ((BigInteger) result[0]).intValue();
		    Double sum = ((Number) result[1]).doubleValue();
		    OportunidadesDisponiblesResult oportunidadDR = new OportunidadesDisponiblesResult(count, sum);
		    oportunidadesDisponibles.add(oportunidadDR);
		}
		model.addAttribute("oportunidadesDisponibles", oportunidadesDisponibles);
		
		List<Object[]> results2 = oportunidadRepository.getOportunidadesTomadas(razonsocial);
		List<OportunidadesTomadasResult> oportunidadesTomadas = new ArrayList<>();

		for (Object[] result : results2) {
		    int count = ((BigInteger) result[0]).intValue();
		    Double sum = ((Number) result[1]).doubleValue();
		    OportunidadesTomadasResult oportunidadTR = new OportunidadesTomadasResult(count, sum);
		    oportunidadesTomadas.add(oportunidadTR);
		}

		model.addAttribute("oportunidadesTomadas", oportunidadesTomadas);
		
		List<Object[]> results3 = oportunidadRepository.getOportunidadesPagadas(razonsocial);
		List<OportunidadesPagadasResult> oportunidadesPagadas = new ArrayList<>();

		for (Object[] result : results3) {
		    int count = ((BigInteger) result[0]).intValue();
		    Double sum = ((Number) result[1]).doubleValue();
		    OportunidadesPagadasResult oportunidadPR = new OportunidadesPagadasResult(count, sum);
		    oportunidadesPagadas.add(oportunidadPR);
		}

		model.addAttribute("oportunidadesPagadas", oportunidadesPagadas);
		return "verOportunidad";
	}
}
