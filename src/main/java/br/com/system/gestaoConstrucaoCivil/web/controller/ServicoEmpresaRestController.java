package br.com.system.gestaoConstrucaoCivil.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.Servico;
import br.com.system.gestaoConstrucaoCivil.service.ServicoService;

@RestController
@RequestMapping("/rest/servico/cadastrarServico")
public class ServicoRestController {

	@Autowired
	 private ServicoService servicoService;
	
	 @RequestMapping(method = RequestMethod.GET, value="/listarServico")
	 public ResponseEntity<Iterable<Servico>> buscarServico() {	  
	  
	  Iterable<Servico> servico = servicoService.buscarTodos();
	  return new ResponseEntity<Iterable<Servico>>(servico, HttpStatus.OK);
	 }
	 
	 @RequestMapping(method = RequestMethod.POST)
	 public ResponseEntity salva(@RequestBody Servico servico,UriComponentsBuilder ucBuilder)
	 {
		 servicoService.salvarOuEditar(servico);
		 HttpHeaders headers = new HttpHeaders();
		 headers.setLocation(ucBuilder.path("/rest/servico/cadastrarServico/{id}").buildAndExpand(servico.getId()).toUri());
		 return new ResponseEntity(headers, HttpStatus.CREATED);
	 }
}
