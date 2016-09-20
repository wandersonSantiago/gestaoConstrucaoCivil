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

import br.com.system.gestaoConstrucaoCivil.entity.ServicoEmpresa;
import br.com.system.gestaoConstrucaoCivil.service.ServicoEmpresaService;

@RestController
@RequestMapping("/rest/servico/vincular")
public class ServicoEmpresaRestController {

	@Autowired
	 private ServicoEmpresaService servicoService;
	
	 @RequestMapping(method = RequestMethod.GET, value="/lista")
	 public ResponseEntity<Iterable<ServicoEmpresa>> buscarServico() {	  
	  Iterable<ServicoEmpresa> servico = servicoService.buscarTodos();
	  return new ResponseEntity<Iterable<ServicoEmpresa>>(servico, HttpStatus.OK);
	 }
	 
	 @RequestMapping( value="/salva", method = RequestMethod.POST)
	 public ResponseEntity salva(@RequestBody ServicoEmpresa servico,UriComponentsBuilder ucBuilder)
	 {
		 System.out.println(servico.getPacoteServico().getDescricao());
		 System.out.println(servico.getPrestadoraServico().getDadoEmpresa().getNomeFantasia());
		
		 servicoService.salvarOuEditar(servico);
		 HttpHeaders headers = new HttpHeaders();
		 headers.setLocation(ucBuilder.path("/rest/servico/vincular/salva/{id}").buildAndExpand(servico.getId()).toUri());
		 return new ResponseEntity(headers, HttpStatus.CREATED);
	 }
}
