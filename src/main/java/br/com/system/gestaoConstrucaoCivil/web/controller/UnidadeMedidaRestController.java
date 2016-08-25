package br.com.system.gestaoConstrucaoCivil.web.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.UnidadeMedida;
import br.com.system.gestaoConstrucaoCivil.enuns.UnidadeMedidaEnum;
import br.com.system.gestaoConstrucaoCivil.service.UnidadeMedidaService;

@RestController
@RequestMapping("/rest/almoxarifado")
public class UnidadeMedidaRestController {

	@Autowired
	 private UnidadeMedidaService unidadeMedidadService;
	
	 @RequestMapping(method = RequestMethod.GET, value="/listarUnidadeMedida")
	 public ResponseEntity<Iterable<UnidadeMedidaEnum>> unidadeMedida() {

	Iterable<UnidadeMedidaEnum> unidadeMedida = Arrays.asList(UnidadeMedidaEnum.values());
	return new ResponseEntity<Iterable<UnidadeMedidaEnum>>(unidadeMedida, HttpStatus.OK);
 }
	 
	 @RequestMapping(method = RequestMethod.POST,value="/cadastrarUnidadeMedida")
	 public ResponseEntity salva(@RequestBody UnidadeMedida unidadeMedida,UriComponentsBuilder ucBuilder)
	 {
		 System.out.println(unidadeMedida.getDescricao());
		 unidadeMedidadService.salvarOuEditar(unidadeMedida);
		 HttpHeaders headers =new HttpHeaders();
		 headers.setLocation(ucBuilder.path("/rest/almoxarifado/cadastrarUnidadeMedida/{id}").buildAndExpand(unidadeMedida.getId()).toUri());
		 return new ResponseEntity(headers, HttpStatus.CREATED);
	 }
}
