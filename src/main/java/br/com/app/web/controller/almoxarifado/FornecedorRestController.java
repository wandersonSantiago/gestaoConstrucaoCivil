package br.com.app.web.controller.almoxarifado;

import java.util.Collection;
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

import br.com.app.entity.almoxarifado.Fornecedor;
import br.com.app.service.almoxarifado.FornecedorService;

@RestController
@RequestMapping("/rest/almoxarifado/fornecedor")
public class FornecedorRestController {

	@Autowired
	private FornecedorService fornecedorService;
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Collection<Fornecedor> buscarTodos() {
		return fornecedorService.buscarTodos();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}")
	public Fornecedor buscarPorId(@PathVariable Long id) {
		return fornecedorService.findById(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void salvar(@RequestBody Fornecedor fornecedor) {
		fornecedorService.insert(fornecedor);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping
	public void alterar(@RequestBody Fornecedor fornecedor) {
		fornecedorService.update(fornecedor);
		 
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/cnpj/{cnpj}")
	public Optional<Fornecedor> buscarPorCnpj(@PathVariable String cnpj) {
		return fornecedorService.buscarPorCNPJ(cnpj);
	}
	
	@GetMapping(value = "/buscar")
	public ResponseEntity<Page<Fornecedor>> findByDescricao(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="dadoEmpresa.nomeFantasia") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction,
			@RequestParam(value="descricao", required = false , defaultValue="")String descricao) {

		Page<Fornecedor> list = null;
		
		if(descricao.isEmpty() || descricao.equalsIgnoreCase("")) {
			list = fornecedorService.findAll(PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}else {
			list = fornecedorService.findByDescricaoIgnoreCase(descricao, PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}
		
		return ResponseEntity.ok().body(list);
	}

}
