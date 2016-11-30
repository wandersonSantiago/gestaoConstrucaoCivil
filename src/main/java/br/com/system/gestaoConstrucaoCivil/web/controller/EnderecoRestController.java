package br.com.system.gestaoConstrucaoCivil.web.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.gestaoConstrucaoCivil.enuns.UfEnum;
import br.com.system.gestaoConstrucaoCivil.service.EnderecoService;

@RestController
@RequestMapping("/rest/endereco")
public class EnderecoRestController {

	@Autowired
	private EnderecoService enderecoService;

	@RequestMapping(method = RequestMethod.GET, value = "/uf")
	public ResponseEntity<Iterable<UfEnum>> uf() {

		Iterable<UfEnum> uf = Arrays.asList(UfEnum.values());
		return new ResponseEntity<Iterable<UfEnum>>(uf, HttpStatus.OK);
	}
}
