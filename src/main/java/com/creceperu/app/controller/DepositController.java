package com.creceperu.app.controller;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.creceperu.app.model.Deposit;
import com.creceperu.app.repository.DepositRepository;

@Controller

public class DepositController {

	   @Autowired
	    private DepositRepository depositRepository;
	   
	   
	   @GetMapping("/deposit")
	    public String showDepositForm(Model model) {
	        model.addAttribute("deposit", new Deposit());
	        model.addAttribute("companies", Arrays.asList(
	                "Arca Continental Lindley S.A.", 
	                "EAFC Maquisistema S.A.", 
	                "Bombonazo Internacional S.A.C.", 
	                "Don Mamino S.A.C."));
	        return "deposit-form";
	    }
	
	   
	   @PostMapping("/deposit")
	    public String processDepositForm(@Valid @ModelAttribute Deposit deposit, BindingResult result) {
	        if (result.hasErrors()) {
	            return "deposit-form";
	        }
	        depositRepository.save(deposit);
	        return "redirect:/deposit";
	    }
}
