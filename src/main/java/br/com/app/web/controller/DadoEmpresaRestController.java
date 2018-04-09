package br.com.app.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.service.DadoEmpresaService;

@RestController
@RequestMapping("/rest/dadoEmpresa")
public class DadoEmpresaRestController {

	@Autowired
	private DadoEmpresaService dadoEmpresaService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/existeCnpj/{cnpj}")
	public Boolean existeCnpj(@PathVariable String cnpj) {

		return dadoEmpresaService.existeCnpjCadastrado(cnpj);

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/existeIe/{ie}")
	public Boolean existeIe(@PathVariable String ie) {

		return dadoEmpresaService.existeIeCadastrado(ie);

	}
}
