package com.creceperu.app.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

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
	
	@GetMapping("/facturas")
    @ResponseBody
    public List<Object[]> getFacturaDataByRuc(@RequestParam("id_empresa") Integer id_empresa) {
        return facturaRepository.findFacturaDataById_empresa(id_empresa);
    }
    
    @PostMapping("/registrarOportunidad")
    public String registrarOportunidad(@RequestParam("empresaId") String empresaId, 
    		@RequestParam("total") double total,  @RequestParam("codigoList") List<String> codigoList,
    		@ModelAttribute("oportunidad") OportunidadDTO oportunidadDTO, EntityManager entityManager) {
    	Integer IDEMPRESA = Integer.parseInt(empresaId);
    	Oportunidad oportunidad = new Oportunidad();
    	oportunidadDTO.setPartes(0);
    	oportunidadDTO.setMonto(total);
    	oportunidadDTO.setFecharegistro(new Date());
    	oportunidadDTO.setId_empresa(IDEMPRESA);
    	oportunidadDTO.setCalificacion("Disponible");  
    	// Persistir la oportunidad en la base de datos
        entityManager.persist(oportunidad);

        // Iterar sobre la lista de códigos y realizar la inserción en la tabla oportunidad_factura
        for (String codigoFactura : codigoList) {
            // Obtener la entidad Factura correspondiente al código actual
            Factura factura = entityManager.find(Factura.class, codigoFactura);

            // Verificar si la factura existe
            if (factura != null) {
                // Asociar la factura con la oportunidad
                oportunidad.getFacturas().add(factura);
                factura.getOportunidades().add(oportunidad);
            }
        }
    	return "redirect:/oportunidad/registroOportunidad?exito";
    }
}
