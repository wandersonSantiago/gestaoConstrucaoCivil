package br.com.app.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.entity.Categoria;
import br.com.app.service.CategoriaService;

@RestController
@RequestMapping("/rest/almoxarifado/categoria")
public class CategoriaRestController {

	@Autowired
	private CategoriaService categoriaService;

	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public Optional<Categoria> findById(@PathVariable Long id) {

		return categoriaService.findById(id);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Categoria insert(@RequestBody @Validated Categoria categoria, BindingResult result) {
		categoriaService.salvarOuEditar(categoria);
		return categoria;
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping
	public void update(@RequestBody Categoria categoria) {
		categoriaService.salvarOuEditar(categoria);
	}
	

	@GetMapping(value = "/descricao")
	public ResponseEntity<Page<Categoria>> findByDepartamentosAndDescricao(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="descricao") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction,
			@RequestParam(value="descricao", required = false , defaultValue="")String descricao) {

		Page<Categoria> list = null;
		
		if(descricao.isEmpty() || descricao.equalsIgnoreCase("")) {
			list = categoriaService.findByDepartamentos(PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}else {
			list = categoriaService.findByDescricaoIgnoreCaseAndCategoriaIsNull(descricao, PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/sub")
	public ResponseEntity<Page<Categoria>> findByDescricaoAndPagination(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="descricao") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction,
			@RequestParam(value="descricao", required = false , defaultValue="")String descricao) {

		Page<Categoria> list = null;
		
		if(descricao.isEmpty() || descricao.equalsIgnoreCase("")) {
			list = categoriaService.findAllByCategoria(PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}else {
			list = categoriaService.findByDescricaoIgnoreCaseAndCategoriaNotNull(descricao, PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}
		
		return ResponseEntity.ok().body(list);
	}
}
