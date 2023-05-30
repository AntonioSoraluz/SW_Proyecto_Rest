package com.creceperu.app.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creceperu.app.controller.dto.OportunidadDTO;
import com.creceperu.app.repository.EmpresaRepository;
import com.creceperu.app.repository.FacturaRepository;
import com.creceperu.app.repository.OportunidadFacturaRepository;
import com.creceperu.app.repository.OportunidadRepository;
import com.creceperu.app.service.OportunidadService;

@Controller
@RequestMapping("/oportunidad")
public class OportunidadController {
	
	@Autowired
	private OportunidadService oportunidadService;
	
	@Autowired
	private OportunidadRepository oportunidadRepository;
	
	@Autowired
	private OportunidadFacturaRepository oportunidadFacturaRepository;
	
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
    
    /*@PostMapping("/registrarOportunidad")
    public String registrarOportunidad(@RequestParam("empresaID") String empresaID, 
    		@RequestParam("total") double total,  @RequestParam("codigoList") List<String> codigoList,
    		@ModelAttribute("oportunidad") OportunidadDTO oportunidadDTO, EntityManager entityManager) {
    	Integer IDEMPRESA = Integer.parseInt(empresaID);
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
    }*/
    @PostMapping("/registrarOportunidad")
    @Transactional
    public String registrarOportunidad(@RequestParam("codigos") String codigos,
    		@ModelAttribute("oportunidad") OportunidadDTO oportunidadDTO) {
    	String lastCode = oportunidadRepository.getLastGeneratedCode();
    	String nextCode = generateNextCode(lastCode);
    	oportunidadDTO.setId_oportunidad(nextCode);
    	oportunidadDTO.setFecharegistro(new Date());
    	oportunidadDTO.setEstado("Disponible");
        oportunidadService.guardar(oportunidadDTO);
        String[] codigoArray = codigos.split(",");
        for (String codigo : codigoArray) {
            oportunidadFacturaRepository.insertOportunidadFactura(nextCode, codigo);
        }
        List<String> codigosList = Arrays.asList(codigoArray);
        facturaRepository.actualizarEstadoFacturas(codigosList);
    	return "redirect:/oportunidad/registroOportunidad?exito";
    }
    
    private String generateNextCode(String lastCode) {
        if (lastCode == null) {
            return "O000001";
        }
        int lastNumber = Integer.parseInt(lastCode.substring(1));
        int nextNumber = lastNumber + 1;
        String nextNumberWithPadding = String.format("%06d", nextNumber);
        return "O" + nextNumberWithPadding;
    }
    
}
