package br.com.system.gestaoConstrucaoCivil.web.controller;

import java.util.List;

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

import br.com.system.gestaoConstrucaoCivil.entity.ConfigEmpreendimentoOutros;
import br.com.system.gestaoConstrucaoCivil.service.ConfigEmpreendimentoOutrosService;

@RestController
@RequestMapping("/rest/servico")
public class ConfigEmpreendimentoOutrosRestController {

	
	  
    @Autowired
    ConfigEmpreendimentoOutrosService configEmpreeendimentoOutrosService;
     
     
    @RequestMapping(value = "/cadastrarConfigEmpreendimentoOutros", method = RequestMethod.POST)
    public ResponseEntity salvarConfigEmpreendimento(@RequestBody List<ConfigEmpreendimentoOutros> configEmpreendimentoOutros, UriComponentsBuilder ucBuilder) {
    	configEmpreeendimentoOutrosService.salvarOuEditar(configEmpreendimentoOutros);
        HttpHeaders headers = new HttpHeaders();
       // headers.setLocation(ucBuilder.path("rest/servico/cadastrarConfigEmpreendimentoOutros/{id}").buildAndExpand(configEmpreendimentoOutros.getId()).toUri());
      
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }
     
    @RequestMapping(method = RequestMethod.GET, value = "/listarConfigEmpreendimentoOutros")
    public ResponseEntity<Iterable<ConfigEmpreendimentoOutros>> buscarConfigEmpreendimento() {
    	Iterable<ConfigEmpreendimentoOutros> configEmpreendimentoOutros = configEmpreeendimentoOutrosService.buscarTodos();
        return new ResponseEntity<Iterable<ConfigEmpreendimentoOutros>>(configEmpreendimentoOutros, HttpStatus.OK);
    }
     
    @RequestMapping(value = "/listarConfiEmpreendimentoOutros/{id}", method = RequestMethod.GET)
    public ResponseEntity<ConfigEmpreendimentoOutros> buscarConfigEmpreendimentoPorId(@PathVariable Long id) {
        return new ResponseEntity<ConfigEmpreendimentoOutros>(configEmpreeendimentoOutrosService.buscarPorId(id), HttpStatus.OK);
    }
    
}
