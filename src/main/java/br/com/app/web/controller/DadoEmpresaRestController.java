package br.com.app.web.controller;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.entity.DadoEmpresa;
import br.com.app.enuns.StatusEnum;
import br.com.app.enuns.UfEnum;
import br.com.app.service.DadoEmpresaService;

@RestController
@RequestMapping("/rest/dadoEmpresa")
public class DadoEmpresaRestController {

	@Autowired
	private DadoEmpresaService dadoEmpresaService;

	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public DadoEmpresa insert(@RequestBody DadoEmpresa empresa) {
		 return dadoEmpresaService.insert(empresa);		 
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/existeCnpj/{cnpj}")
	public Boolean existeCnpj(@PathVariable String cnpj) {
		return dadoEmpresaService.existeCnpjCadastrado(cnpj);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/cnpj/{cnpj}")
	public DadoEmpresa findByCnpj(@PathVariable String cnpj) {
		return dadoEmpresaService.findByCnpj(cnpj);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/existeIe/{ie}")
	public Boolean existeIe(@PathVariable String ie) {
		return dadoEmpresaService.existeIeCadastrado(ie);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/status")
	public Collection<StatusEnum> status() {
		return Arrays.asList(StatusEnum.values());
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/uf")
	public Collection<UfEnum> ufs() {
		return Arrays.asList(UfEnum.values());
	}
}
