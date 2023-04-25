package com.creceperu.app.service;

import com.creceperu.app.model.CuentaBancariaa;
import com.creceperu.app.repository.CuentaBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaBancariaServiceIMPL implements CuentaBancariaService {

    @Autowired
    private CuentaBancariaRepository cuentaBancariaRepository;

    @Override
    public List<CuentaBancariaa> ConsultarCuentaBancariaa() {
        return (List<CuentaBancariaa>) this.cuentaBancariaRepository.findAll();
    }

    @Override
    public CuentaBancariaa CrearCuentaBancariaa(CuentaBancariaa cuentaBancariaa) {
        return this.cuentaBancariaRepository.save(cuentaBancariaa);
    }

    @Override
    public CuentaBancariaa ModificarCuentaBancariaa(CuentaBancariaa cuentaBancariaa) {
        return this.cuentaBancariaRepository.save(cuentaBancariaa);
    }

    @Override
    public CuentaBancariaa BuscarCuentaBancariaa(long id) {
        return this.cuentaBancariaRepository.findById(id).get();
    }

    @Override
    public void EliminarrCuentaBancariaa(long id) {
        this.cuentaBancariaRepository.deleteById(id);
    }
}
