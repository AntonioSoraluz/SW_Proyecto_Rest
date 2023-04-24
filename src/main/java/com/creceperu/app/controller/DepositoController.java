package com.creceperu.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creceperu.app.model.CuentaBancaria;

@Controller
public class DepositoController {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	
	 @GetMapping("/deposito")
	  public String depositoForm(Model model) {
	    model.addAttribute("cuenta", new CuentaBancaria());
	    return "deposito";
	  }
	
	 @PostMapping("/deposito")
	  public String depositoSubmit(@ModelAttribute CuentaBancaria cuenta) {
	    double cantidad = cuenta.getSaldoActual();
	    int numeroCuenta = cuenta.getNumeroCuenta();
	    jdbcTemplate.update("UPDATE cuenta_bancaria SET saldo_actual = saldo_actual + ? WHERE numero_cuenta = ?", cantidad, numeroCuenta);
	    return "redirect:/deposito";
	  }
	 
	 
	 @RequestMapping("/")
	   public String index() {
	      return "index";
	   }
}
