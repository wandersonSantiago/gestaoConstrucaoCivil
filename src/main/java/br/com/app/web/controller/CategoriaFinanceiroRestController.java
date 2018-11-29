package br.com.app.web.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
import br.com.app.entity.CategoriaFinanceiro;
import br.com.app.enuns.CategoriaEnum;
import br.com.app.enuns.UfEnum;
import br.com.app.service.CategoriaFinanceiroService;

@RestController
@RequestMapping("/rest/financeiro/categoria")
public class CategoriaFinanceiroRestController {

	@Autowired
	private CategoriaFinanceiroService categoriaService;

	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public CategoriaFinanceiro findById(@PathVariable Long id) {

		return categoriaService.findById(id);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public CategoriaFinanceiro insert(@RequestBody CategoriaFinanceiro categoria, BindingResult result) {
		categoriaService.insert(categoria);
		return categoria;
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping
	public void update(@RequestBody CategoriaFinanceiro categoria) {
		categoriaService.update(categoria);
	}
	
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/tipo/{catEnum}")
	public List<CategoriaFinanceiro> findByCategorias(@PathVariable CategoriaEnum catEnum) {

		return categoriaService.findByCategorias(catEnum);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/id/{id}")
	public List<CategoriaFinanceiro> findByCategoriaFinanceiroId(@PathVariable Long id) {

		return categoriaService.findByCategoriaFinanceiroId(id);
	}
	
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/tipo")
	public Collection<CategoriaEnum> ufs() {
		return Arrays.asList(CategoriaEnum.values());
	}
	
	@GetMapping(value = "/descricao")
	public ResponseEntity<Page<CategoriaFinanceiro>> findAll(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="descricao") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction,
			@RequestParam(value="descricao", required = false , defaultValue="")String descricao) {

		Page<CategoriaFinanceiro> list = null;
		
		if(descricao.isEmpty() || descricao.equalsIgnoreCase("")) {
			list = categoriaService.findByAll(PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}else {
			list = categoriaService.findByDescricaoIgnoreCase(descricao, PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}
		
		return ResponseEntity.ok().body(list);
	}
}
