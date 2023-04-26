package com.creceperu.app.controller;

import com.creceperu.app.model.CuentaBancaria;
import com.creceperu.app.model.CuentaBancariaa;
import com.creceperu.app.service.CuentaBancariaService;
import com.creceperu.app.service.CuentaBancariaServiceIMPL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistroCuentaBancariaController {

    private final Logger logger = LoggerFactory.getLogger(RegistroCuentaBancariaController.class);
    @Autowired
    private CuentaBancariaServiceIMPL ctaIMPL;
    @GetMapping("/CuentaBancaria")
    public String ConsultarCuentasBancarias(Model model){
        logger.info(" -- paso 1 --");
        List<CuentaBancariaa> listarCuentaBancarias = ctaIMPL.ConsultarCuentaBancariaa();
        logger.info(" -- paso 2  listarCuentaBancarias -- {} ", listarCuentaBancarias.size());

        model.addAttribute("lista", listarCuentaBancarias);
        return "cuentaBancaria";
    }

    @GetMapping("/CuentaBancaria/registrar")
    public String mostrarFormularioDeRegistrtarCuentaBancaria(Model modelo) {
        CuentaBancariaa cuentaBancariaa = new CuentaBancariaa();
        modelo.addAttribute("cuentaBancaria", cuentaBancariaa);
        return "FormRegCuentaBancaria";
    }
    @PostMapping("/CuentaBancaria")
    public String CrearCuentaBancariaa(@ModelAttribute("cuentaBancaria") CuentaBancariaa cuentaBancariaa) {
        ctaIMPL.CrearCuentaBancariaa(cuentaBancariaa);
        return "redirect:/CuentaBancaria";
    }


}
