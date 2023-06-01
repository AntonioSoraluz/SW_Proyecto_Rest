package com.creceperu.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.creceperu.app.model.Oportunidad;

@Repository
public interface OportunidadRepository extends JpaRepository<Oportunidad, String>{
	@Query(value = "SELECT id_oportunidad FROM Oportunidad ORDER BY id_oportunidad DESC LIMIT 1", nativeQuery = true)
	String getLastGeneratedCode();
	@Query(value = "SELECT * FROM oportunidad where estado in ('Disponible')", nativeQuery = true)
	List<Oportunidad> findAllOportunidades();
}
