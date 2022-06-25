package br.com.app.commons.api.v1.resource;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.commons.domain.enuns.StatusEnum;
import br.com.app.commons.domain.enuns.UfEnum;
import br.com.app.commons.domain.model.DadoEmpresa;
import br.com.app.commons.domain.service.DadoEmpresaService;

@RestController
@RequestMapping("/rest/dadoEmpresa")
public class DadoEmpresaResource {

	@Autowired
	private DadoEmpresaService dadoEmpresaService;

	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public DadoEmpresa insert(@RequestBody DadoEmpresa empresa) {
		 return dadoEmpresaService.insert(empresa);		 
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping
	public DadoEmpresa update(@RequestBody DadoEmpresa empresa) {
		 return dadoEmpresaService.update(empresa);		 
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}")
	public DadoEmpresa buscarPorId(@PathVariable Long id) {
		return dadoEmpresaService.findById(id);
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
	
	@GetMapping(value = "/buscar")
	public ResponseEntity<Page<DadoEmpresa>> findByDescricao(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nomeFantasia") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction,
			@RequestParam(value="descricao", required = false , defaultValue="")String descricao) {

		Page<DadoEmpresa> list = null;
		
		if(descricao.isEmpty() || descricao.equalsIgnoreCase("")) {
			list = dadoEmpresaService.findAll(PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}else {
			list = dadoEmpresaService.findByDescricaoIgnoreCase(descricao, PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}
		
		return ResponseEntity.ok().body(list);
	}
}
