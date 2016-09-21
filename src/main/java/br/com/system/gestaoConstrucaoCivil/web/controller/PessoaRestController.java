package br.com.system.gestaoConstrucaoCivil.web.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.gestaoConstrucaoCivil.enuns.EstadoCivilEnum;
import br.com.system.gestaoConstrucaoCivil.service.PessoaService;

@RestController
@RequestMapping("/rest/pessoa")
public class PessoaRestController {
 
	@Autowired
	private PessoaService pessoaService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/existeCpf/{cpf}")
	public ResponseEntity existeCpf(@PathVariable String cpf){
		
		return new ResponseEntity(pessoaService.existeCpf(cpf), HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.GET, value = "/existeRg/{rg}")
	public ResponseEntity existeRg(@PathVariable String rg){
		
		return new ResponseEntity(pessoaService.existeRg(rg), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/estadoCivil")
	 public ResponseEntity<Iterable<EstadoCivilEnum>> estadoCivil() {

	Iterable<EstadoCivilEnum> estadoCivil = Arrays.asList(EstadoCivilEnum.values());
	return new ResponseEntity<Iterable<EstadoCivilEnum>>(estadoCivil, HttpStatus.OK);
 }
}
