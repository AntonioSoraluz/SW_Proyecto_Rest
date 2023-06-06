package com.creceperu.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.creceperu.app.model.CuentaBancaria;

@Repository
public interface CuentaBancariaRepository extends JpaRepository<CuentaBancaria,Integer>{
	List<CuentaBancaria> findByObjUsuarioId(Long id);
	boolean existsByNumeroCuenta(String numeroCuenta);
	@Modifying
    @Query("UPDATE CuentaBancaria c SET c.monto = c.monto + :monto WHERE c.id_cuentaBancaria = :idCuentaBancaria")
    void actualizarMontoCuentaBancaria(@Param("idCuentaBancaria") Integer idCuentaBancaria, @Param("monto") Double monto);
}
