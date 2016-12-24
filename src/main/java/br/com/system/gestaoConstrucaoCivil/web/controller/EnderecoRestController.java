package br.com.system.gestaoConstrucaoCivil.web.controller;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.gestaoConstrucaoCivil.enuns.UfEnum;

@RestController
@RequestMapping("/rest/endereco")
public class EnderecoRestController {


	@GetMapping(value = "/uf")
	public ResponseEntity<Iterable<UfEnum>> uf() {

		return new ResponseEntity<Iterable<UfEnum>>(Arrays.asList(UfEnum.values()), HttpStatus.OK);
	}
}
