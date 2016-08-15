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

import br.com.system.gestaoConstrucaoCivil.entity.PacoteServico;
import br.com.system.gestaoConstrucaoCivil.service.PacoteServicoService;

@RestController
@RequestMapping("/rest/pacoteServico")
public class PacoteServicoRestController {

	@Autowired
	 private PacoteServicoService pacoteServicoService;
	
	 @RequestMapping(method = RequestMethod.GET, value="/listarPacoteServico")
	 public ResponseEntity<Iterable<PacoteServico>> buscarPacoteServico() {	  
	  
	  Iterable<PacoteServico> pacoteServico = pacoteServicoService.buscarTodos();
	  return new ResponseEntity<Iterable<PacoteServico>>(pacoteServico, HttpStatus.OK);
	 }
	 
	 @RequestMapping(value="/cadastrarPacoteServico", method = RequestMethod.POST)
	 public ResponseEntity salva(@RequestBody PacoteServico pacoteServico,UriComponentsBuilder ucBuilder)
	 {	 pacoteServicoService.salvarOuEditar(pacoteServico);
		 HttpHeaders headers =new HttpHeaders();
		 headers.setLocation(ucBuilder.path("/rest/pacoteServico/cadastrarPacoteServico/{id}").buildAndExpand(pacoteServico.getId()).toUri());
		 return new ResponseEntity(headers, HttpStatus.CREATED);
	 }
	
}
