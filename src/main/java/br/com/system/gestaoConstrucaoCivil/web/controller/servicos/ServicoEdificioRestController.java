package br.com.system.gestaoConstrucaoCivil.web.controller.servicos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.servicos.ServicoEdificio;
import br.com.system.gestaoConstrucaoCivil.service.servicos.ServicoEdificioService;

@RestController
@RequestMapping("/rest/servico/edificio/vincular")
public class ServicoEdificioRestController {
	
	@Autowired
	private ServicoEdificioService servicoEdificioService; 
	
	 @PostMapping( value="/salva")
	 public ResponseEntity<ServicoEdificio> salvar(@RequestBody ServicoEdificio servico,UriComponentsBuilder ucBuilder)
	 {
		 servicoEdificioService.salvarOuEditar(servico);
		 HttpHeaders headers = new HttpHeaders();
		 headers.setLocation(ucBuilder.path("/rest/servico/vincular/salva/{id}").buildAndExpand(servico.getId()).toUri());
		 return new ResponseEntity(headers, HttpStatus.CREATED);
	 }
	
}
