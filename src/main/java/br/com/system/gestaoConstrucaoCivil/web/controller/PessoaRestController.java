package br.com.system.gestaoConstrucaoCivil.web.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.gestaoConstrucaoCivil.enuns.EstadoCivilEnum;
import br.com.system.gestaoConstrucaoCivil.service.PessoaService;

@RestController
@RequestMapping("/rest/pessoa")
public class PessoaRestController {
 
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping(value = "/existeCpf/{cpf}")
	public ResponseEntity existeCpf(@PathVariable String cpf){
		
		return new ResponseEntity(pessoaService.existeCpf(cpf), HttpStatus.OK);
	}
	@GetMapping(value = "/existeRg/{rg}")
	public ResponseEntity existeRg(@PathVariable String rg){
		
		return new ResponseEntity(pessoaService.existeRg(rg), HttpStatus.OK);
	}
	
	@GetMapping(value="/estadoCivil")
	 public ResponseEntity<Iterable<EstadoCivilEnum>> estadoCivil() {

		return new ResponseEntity<Iterable<EstadoCivilEnum>>(Arrays.asList(EstadoCivilEnum.values()), HttpStatus.OK);
 }
}
