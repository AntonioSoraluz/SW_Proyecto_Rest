package com.creceperu.app.service;

import com.creceperu.app.model.CuentaBancariaa;

import java.util.List;

public interface CuentaBancariaService {
    public List<CuentaBancariaa> ConsultarCuentaBancariaa();
    public CuentaBancariaa CrearCuentaBancariaa(CuentaBancariaa cuentaBancariaa);
    public CuentaBancariaa ModificarCuentaBancariaa(CuentaBancariaa cuentaBancariaa);
    public CuentaBancariaa BuscarCuentaBancariaa(long id);
    public void EliminarrCuentaBancariaa(long id);

}
