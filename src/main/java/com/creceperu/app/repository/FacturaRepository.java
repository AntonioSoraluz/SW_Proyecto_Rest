package com.creceperu.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.creceperu.app.model.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura,Integer>{
	@Query(value = "SELECT id_factura FROM Factura ORDER BY id_factura DESC LIMIT 1", nativeQuery = true)
    String getLastGeneratedCode();
}
