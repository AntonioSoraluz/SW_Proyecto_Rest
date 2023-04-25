package com.creceperu.app.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class RegistroCuentaBancariaController {

    @GetMapping("/CuentaBancaria")
    public String depositoForm(Model model) {
//        model.addAttribute("cuenta", new CuentaBancaria());
        return "CuentaBancaria";
    }
}
