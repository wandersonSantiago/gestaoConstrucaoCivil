package br.com.system.gestaoConstrucaoCivil.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.ConfigEmpreendimentoEdificio;
import br.com.system.gestaoConstrucaoCivil.pojo.ConfigEmpreendimentoEdificioPojo;
import br.com.system.gestaoConstrucaoCivil.service.ConfigEmpreendimentoEdificioService;


@RestController
@RequestMapping("/rest/empreendimento/configuracao")
public class ConfigEmpreendimentoEdificioRestController {

	 @Autowired
	 ConfigEmpreendimentoEdificioService configEmpreeendimentoEdificioService;
	
	@RequestMapping(value = "/salvaEdificio", method = RequestMethod.POST)
    public ResponseEntity salvarConfigEmpreendimentoEdificio(@RequestBody ConfigEmpreendimentoEdificio configEmpreendimentoEdificio, UriComponentsBuilder ucBuilder) {
        
    	
    	configEmpreeendimentoEdificioService.salvarOuEditar(configEmpreendimentoEdificio);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("rest/empreendimento/configuracao/salvaEdificio/{configEmpreendimento}").buildAndExpand(configEmpreendimentoEdificio.getId()).toUri());
     
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "/listaEdificio/{id}", method = RequestMethod.GET)
    public ResponseEntity<ConfigEmpreendimentoEdificio> buscarConfigEmpreendimentoEdificioPorId(@PathVariable Long id) {
        return new ResponseEntity<ConfigEmpreendimentoEdificio>(configEmpreeendimentoEdificioService.buscarPorId(id), HttpStatus.OK);
    }
	
	@RequestMapping(value = "",method = RequestMethod.GET)
	public ResponseEntity<ConfigEmpreendimentoEdificioPojo> configPojo() {
        
        return new ResponseEntity<ConfigEmpreendimentoEdificioPojo>(configEmpreeendimentoEdificioService.getConfig(),HttpStatus.OK);
	}
}
