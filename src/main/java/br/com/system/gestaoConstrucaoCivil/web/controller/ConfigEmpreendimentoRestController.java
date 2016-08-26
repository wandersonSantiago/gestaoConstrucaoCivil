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
 
import br.com.system.gestaoConstrucaoCivil.entity.ConfigEmpreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.ConfigEmpreendimentoCasa;
import br.com.system.gestaoConstrucaoCivil.entity.ConfigEmpreendimentoEdificio;
import br.com.system.gestaoConstrucaoCivil.service.ConfigEmpreendimentoService;
 
@RestController
@RequestMapping("/rest/servico")
public class ConfigEmpreendimentoRestController {
     
    @Autowired
    ConfigEmpreendimentoService configEmpreeendimentoService;
     
    /* 
    @RequestMapping(value = "/cadastrarConfigEmpreendimentoEdificio", method = RequestMethod.POST)
    public ResponseEntity salvarConfigEmpreendimentoEdificio(@RequestBody ConfigEmpreendimentoEdificio configEmpreendimentoEdificio, UriComponentsBuilder ucBuilder) {
        
    	if(configEmpreendimentoEdificio.getEmpreendimento()==null)
    	{
    		System.out.println("SIM");
    	}
    	configEmpreeendimentoService.salvarOuEditar(configEmpreendimentoEdificio);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("rest/servico/cadastrarConfigEmpreendimentoEdificio/{configEmpreendimento}").buildAndExpand(configEmpreendimentoEdificio.getId()).toUri());
     
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }*//*
    @RequestMapping(value = "/cadastrarConfigEmpreendimentoCasa", method = RequestMethod.POST)
    public ResponseEntity salvarConfigEmpreendimentoCasa(@RequestBody ConfigEmpreendimentoCasa configEmpreendimentoCasa, UriComponentsBuilder ucBuilder) {
        configEmpreeendimentoService.salvarOuEditar(configEmpreendimentoCasa);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("rest/servico/cadastrarConfigEmpreendimentoCasa/{configEmpreendimento}").buildAndExpand(configEmpreendimentoCasa.getId()).toUri());
    
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }
     */
    @RequestMapping(method = RequestMethod.GET, value = "/listarConfigEmpreendimento")
    public ResponseEntity<Iterable<ConfigEmpreendimento>> buscarConfigEmpreendimento() {
 
        Iterable<ConfigEmpreendimento> configEmpreendimento = configEmpreeendimentoService.buscarTodos();
        return new ResponseEntity<Iterable<ConfigEmpreendimento>>(configEmpreendimento, HttpStatus.OK);
    }
     
    @RequestMapping(value = "/listarConfiEmpreendimento/{id}", method = RequestMethod.GET)
    public ResponseEntity<ConfigEmpreendimento> buscarConfigEmpreendimentoPorId(@PathVariable Long id) {
        return new ResponseEntity<ConfigEmpreendimento>(configEmpreeendimentoService.buscarPorId(id), HttpStatus.OK);
    }
     
     
}