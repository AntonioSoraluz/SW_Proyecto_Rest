package com.creceperu.app.controller;

import com.creceperu.app.model.CuentaBancaria;
import com.creceperu.app.model.CuentaBancariaa;
import com.creceperu.app.model.TipoBanco;
import com.creceperu.app.model.TipoCuentaBancaria;
import com.creceperu.app.service.CuentaBancariaService;
import com.creceperu.app.service.CuentaBancariaServiceIMPL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistroCuentaBancariaController {

    private final Logger logger = LoggerFactory.getLogger(RegistroCuentaBancariaController.class);
    @Autowired
    private CuentaBancariaServiceIMPL ctaIMPL;
    @GetMapping("/listarCuentaBancaria")
    public String ConsultarCuentasBancarias(Model model){

        return "listarCuentaBancaria";
    }


    @GetMapping("/CuentaBancaria/registrar")
    public String mostrarFormularioDeRegistrtarCuentaBancaria(Model modelo) {
        CuentaBancariaa cuentaBancariaa = new CuentaBancariaa();
        modelo.addAttribute("cuentaBancariaa", cuentaBancariaa);
        return "FormRegCuentaBancaria";
    }


    @PostMapping("/crearCuentaBancaria")
    public String CrearCuentaBancariaa(CuentaBancariaa cuenta, BindingResult result) {
        logger.info(" -- registrar cuenta --: {}",cuenta.getId_banco());
        ctaIMPL.CrearCuentaBancariaa(cuenta);
        return "listarCuentaBancaria";
    }



}
