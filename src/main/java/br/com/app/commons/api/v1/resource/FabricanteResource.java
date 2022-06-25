package br.com.app.commons.api.v1.resource;

import java.util.Optional;

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

import br.com.app.commons.domain.model.Fabricante;
import br.com.app.commons.domain.service.FabricanteService;

@RestController
@RequestMapping("/rest/almoxarifado/fabricante")
public class FabricanteResource {

	@Autowired
	private FabricanteService fabricanteService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void insert(@RequestBody Fabricante fabricante) {
		fabricanteService.insert(fabricante);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping
	public void update(@RequestBody Fabricante fabricante) {
		fabricanteService.update(fabricante);

	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}")
	public Optional<Fabricante> buscarPorId(@PathVariable Long id) {
		return fabricanteService.findById(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/cnpj/{cnpj}")
	public Optional<Fabricante> buscarPorCnpj(@PathVariable String cnpj) {
		return fabricanteService.buscarPorCNPJ(cnpj);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/existeCnpj/{cnpj}")
	public Boolean existeCnpj(@PathVariable String cnpj) {
		return fabricanteService.existeCnpjCadastrado(cnpj);
	}
	
	@GetMapping(value = "/buscar")
	public ResponseEntity<Page<Fabricante>> findByDescricao(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="dadoEmpresa.nomeFantasia") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction,
			@RequestParam(value="descricao", required = false , defaultValue="")String descricao) {

		Page<Fabricante> list = null;
		
		if(descricao.isEmpty() || descricao.equalsIgnoreCase("")) {
			list = fabricanteService.findAll(PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}else {
			list = fabricanteService.findByDescricaoIgnoreCase(descricao, PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}
		
		return ResponseEntity.ok().body(list);
	}
}
