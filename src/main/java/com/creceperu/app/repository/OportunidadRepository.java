package com.creceperu.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creceperu.app.model.Oportunidad;

@Repository
public interface OportunidadRepository extends JpaRepository<Oportunidad, Integer>{

}
