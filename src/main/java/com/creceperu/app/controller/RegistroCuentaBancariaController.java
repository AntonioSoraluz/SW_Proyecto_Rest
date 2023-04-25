package com.creceperu.app.controller;

import com.creceperu.app.model.CuentaBancariaa;
import com.creceperu.app.service.CuentaBancariaService;
import com.creceperu.app.service.CuentaBancariaServiceIMPL;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("CRUDrepo")
public class RegistroCuentaBancariaController {

    private CuentaBancariaServiceIMPL ctaIMPL;

    @GetMapping()
    @RequestMapping(value="ConsultarCuentasBancarias", method = RequestMethod.GET)
    public ResponseEntity<?> ConsultarCuentasBancarias(){
        List<CuentaBancariaa> listarCuentaBancarias = this.ctaIMPL.ConsultarCuentaBancariaa();
        return ResponseEntity.ok(listarCuentaBancarias);
    }



}
