package com.creceperu.app.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creceperu.app.model.Usuario;
import com.creceperu.app.repository.UsuarioRepository;

@Service
@Transactional
public class CapitalistaService {

	@Autowired UsuarioRepository usuarioRepository;
	
	public void init() {
		if(usuarioRepository.findAll().isEmpty()) {
			Usuario usuario = new Usuario();
			usuario.setNombres("Luis Alberto");
			create(usuario);
			
			usuario = new Usuario();
			usuario.setNombres("Luis Alberto");
			create(usuario);
		}
	}
	
	public Usuario create(Usuario usuario) {
		if (usuario.getNombres() == null || usuario.getNombres().isEmpty()) {
			throw new RuntimeException();
		}
		
		return usuarioRepository.save(usuario);
	}
	
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
}
