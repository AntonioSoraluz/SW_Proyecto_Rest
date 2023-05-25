package com.creceperu.app.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.creceperu.app.controller.dto.OportunidadDTO;
import com.creceperu.app.model.Factura;
import com.creceperu.app.model.Oportunidad;
import com.creceperu.app.repository.EmpresaRepository;
import com.creceperu.app.repository.FacturaRepository;
import com.creceperu.app.repository.OportunidadRepository;
import com.creceperu.app.service.OportunidadService;

@Controller
@RequestMapping("/oportunidad")
public class OportunidadController {
	
	@Autowired
	private OportunidadService oportunidadService;
	
	@Autowired
	private OportunidadRepository oportinudadRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private FacturaRepository facturaRepository;
	
	public OportunidadController (OportunidadService oportunidadService) {
		super();
		this.oportunidadService = oportunidadService;
	}
	
	@ModelAttribute("oportunidad")
	public OportunidadDTO oportunidadDTO() {
		return new OportunidadDTO();
	}
	
	@GetMapping("/registroOportunidad")
	public String mostrarRegistroDeOportunidad(Model model) {
		model.addAttribute("lstEmpresa", empresaRepository.findAll());
		return "oportunidad";
	}
	/*@PostMapping("/buscarFactura")
    public String buscarFactura(@RequestParam("empresaRuc") String empresaRuc, Model model) {
		
        model.addAttribute("lstFacturas", facturasListadas);
        model.addAttribute("lstEmpresa", empresaRepository.findAll());
        return "oportunidad";
    }*/
	/*@GetMapping("/buscarFacturas")
	@ResponseBody
	public List<Factura> consulta(String empresaRuc){
		List<Factura> facturas = facturaRepository.findByRuc(empresaRuc);
		/*List<Factura> facturasListadas = facturas.stream()
	            .filter(factura -> factura.getEstado().equals("Listada"))
	            .collect(Collectors.toList());*/
		/*return facturas;
	}*/
	@GetMapping("/facturas")
    @ResponseBody
    public List<Object[]> getInvoiceDataByRuc(@RequestParam("ruc") String ruc) {
        return facturaRepository.findFacturaDataByRuc(ruc);
    }
}
