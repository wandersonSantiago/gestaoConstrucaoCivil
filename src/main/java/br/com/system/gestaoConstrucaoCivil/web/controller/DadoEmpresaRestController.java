package br.com.system.gestaoConstrucaoCivil.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.gestaoConstrucaoCivil.service.DadoEmpresaService;

@RestController
@RequestMapping("/rest/dadoEmpresa")
public class DadoEmpresaRestController {

	
	 @Autowired
	 private DadoEmpresaService dadoEmpresaService;
	 
	 @RequestMapping(method = RequestMethod.GET, value = "/existeCnpj")
		public ResponseEntity existeCnpj(@RequestBody String cnpj) {
			
			 return new ResponseEntity(dadoEmpresaService.existeCnpjCadastrado(cnpj), HttpStatus.OK);

		}
		@RequestMapping(method = RequestMethod.GET, value = "/existeIe")
		public ResponseEntity existeIe(@RequestBody String ie) {
			
			 return new ResponseEntity(dadoEmpresaService.existeIeCadastrado(ie), HttpStatus.OK);

		}
}
