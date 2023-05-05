package com.creceperu.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creceperu.app.model.Empresa;

@Repository
public interface EmpresaReposoitory extends JpaRepository<Empresa, Integer>{

}
