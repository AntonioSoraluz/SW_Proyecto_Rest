package com.creceperu.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.creceperu.app.model.Deposit;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {

}
