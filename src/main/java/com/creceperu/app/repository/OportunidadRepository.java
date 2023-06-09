package com.creceperu.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.creceperu.app.model.Oportunidad;
import com.creceperu.app.model.OportunidadesDisponiblesResult;
import com.creceperu.app.model.OportunidadesPagadasResult;
import com.creceperu.app.model.OportunidadesTomadasResult;

@Repository
public interface OportunidadRepository extends JpaRepository<Oportunidad, String>{
	@Query(value = "SELECT id_oportunidad FROM Oportunidad ORDER BY id_oportunidad DESC LIMIT 1", nativeQuery = true)
	String getLastGeneratedCode();
	@Query(value = "SELECT * FROM oportunidad where estado in ('Disponible')", nativeQuery = true)
	List<Oportunidad> findAllOportunidades();
	@Query("SELECT o FROM Oportunidad o INNER JOIN o.objEmpresa e WHERE o.estado = 'Disponible' AND ((:filtro = '') OR (o.calificacion = :filtro OR e.razonsocial = :filtro)) ORDER BY o.fecharegistro")
	List<Oportunidad> findOportunidadesXFiltro(@Param("filtro") String filtro);
	@Query("SELECT o FROM Oportunidad o WHERE o.id_oportunidad = :idOportunidad")
    Oportunidad findByOportunidadId(@Param("idOportunidad") String idOportunidad);
	/*
	@Query(value = "SELECT COUNT(o.estado) AS oportunidadesdisponibles, COALESCE(SUM(o.monto),0) AS montooportunidadesdisponibles FROM empresa e INNER JOIN oportunidad o ON e.id_empresa = o.id_empresa WHERE o.estado = 'Disponible' AND e.razonsocial = :razonsocial", nativeQuery = true)
	OportunidadesDisponiblesResult getOportunidadesDisponibles(@Param("razonsocial") String razonsocial);*/
	
	@Query(value = "SELECT COUNT(o.estado) AS oportunidadesdisponibles, COALESCE(SUM(o.monto),0) AS montooportunidadesdisponibles FROM empresa e INNER JOIN oportunidad o ON e.id_empresa = o.id_empresa WHERE o.estado = 'Disponible' AND e.razonsocial = :razonsocial", nativeQuery = true)
	List<Object[]> getOportunidadesDisponibles(@Param("razonsocial") String razonsocial);

	/*
	@Query(value = "SELECT COUNT(o.estado) AS oportunidadestomadas, COALESCE(SUM(o.monto),0) AS montooportunidadestomadas FROM empresa e INNER JOIN oportunidad o ON e.id_empresa = o.id_empresa WHERE o.estado = 'Tomada' AND e.razonsocial = :razonsocial", nativeQuery = true)
	OportunidadesTomadasResult getOportunidadesTomadas(@Param("razonsocial") String razonsocial);*/
	
	@Query(value = "SELECT COUNT(o.estado) AS oportunidadestomadas, COALESCE(SUM(o.monto),0) AS montooportunidadestomadas FROM empresa e INNER JOIN oportunidad o ON e.id_empresa = o.id_empresa WHERE o.estado = 'Tomada' AND e.razonsocial = :razonsocial", nativeQuery = true)
	List<Object[]> getOportunidadesTomadas(@Param("razonsocial") String razonsocial);

	/*
	@Query(value = "SELECT COUNT(o.estado) AS oportunidadespagadas, COALESCE(SUM(o.monto),0) AS montooportunidadespagadas FROM empresa e INNER JOIN oportunidad o ON e.id_empresa = o.id_empresa WHERE o.estado = 'Pagada' AND e.razonsocial = :razonsocial", nativeQuery = true)
	OportunidadesPagadasResult getOportunidadesPagadas(@Param("razonsocial") String razonsocial);*/
	
	@Query(value = "SELECT COUNT(o.estado) AS oportunidadespagadas, COALESCE(SUM(o.monto),0) AS montooportunidadespagadas FROM empresa e INNER JOIN oportunidad o ON e.id_empresa = o.id_empresa WHERE o.estado = 'Pagada' AND e.razonsocial = :razonsocial", nativeQuery = true)
	List<Object[]> getOportunidadesPagadas(@Param("razonsocial") String razonsocial);



}
