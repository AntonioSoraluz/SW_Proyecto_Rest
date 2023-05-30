package com.creceperu.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creceperu.app.model.OportunidadUsuario;

@Repository
public interface OportunidadUsuarioRepository extends JpaRepository<OportunidadUsuario, String>{

}
