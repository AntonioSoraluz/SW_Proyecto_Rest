package com.creceperu.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.creceperu.app.model.Empresa;
import com.creceperu.app.model.Usuario;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

	public Empresa findByRuc(String ruc);
}
