package br.com.app.web.controller;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.enuns.UfEnum;

@RestController
@RequestMapping("/rest/endereco")
public class EnderecoRestController {


	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/uf")
	public Collection<UfEnum> uf() {

		return  Arrays.asList(UfEnum.values());
	}
}
