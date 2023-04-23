package com.creceperu.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creceperu.app.model.Capitalista;

@Repository
public interface CapitalistaRepository extends JpaRepository<Capitalista, Long>{

}
