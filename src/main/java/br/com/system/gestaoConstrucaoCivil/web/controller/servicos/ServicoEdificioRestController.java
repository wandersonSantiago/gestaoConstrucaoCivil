package br.com.system.gestaoConstrucaoCivil.web.controller.servicos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.servicos.ServicoEdificio;
import br.com.system.gestaoConstrucaoCivil.service.servicos.ServicoEdificioService;

@RestController
@RequestMapping("/rest/servico/edificio/vincular")
public class ServicoEdificioRestController {
	
	@Autowired
	private ServicoEdificioService servicoEdificioService; 
	
	 @RequestMapping( value="/salva", method = RequestMethod.POST)
	 public ResponseEntity salva(@RequestBody ServicoEdificio servico,UriComponentsBuilder ucBuilder)
	 {
		 System.out.println(servico.getPacoteServico().getDescricao());
		 System.out.println(servico.getPrestadoraServico().getDadoEmpresa().getNomeFantasia());
		 System.out.println(servico.getTorre());
		 servicoEdificioService.salvarOuEditar(servico);
		 HttpHeaders headers = new HttpHeaders();
		//headers.setLocation(ucBuilder.path("/rest/servico/vincular/salva/{id}").buildAndExpand(servico.getId()).toUri());
		 return new ResponseEntity(headers, HttpStatus.CREATED);
	 }

}
