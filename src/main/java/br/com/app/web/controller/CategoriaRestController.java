package br.com.app.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.entity.Categoria;
import br.com.app.enuns.TipoCategoriaEnum;
import br.com.app.service.CategoriaService;

@RestController
@RequestMapping("/rest/almoxarifado/categoria")
public class CategoriaRestController {

	@Autowired
	private CategoriaService categoriaService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista")
	public List<Categoria> buscarTodos() {
		return categoriaService.buscarTodos();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscaPorId/{id}")
	public Optional<Categoria> buscarPorId(@PathVariable Long id) {

		return categoriaService.findById(id);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salva")
	public void salvar(@RequestBody @Validated Categoria categoria, BindingResult result) {

		CategoriaValidator v = new CategoriaValidator();
		v.validate(categoria, result);
		categoriaService.salvarOuEditar(categoria);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/altera")
	public void alterar(@RequestBody Categoria categoria) {
		categoriaService.salvarOuEditar(categoria);
	}
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/tipoCategoria")
	public List<TipoCategoriaEnum> tipoCategoria() {
		return Arrays.asList(TipoCategoriaEnum.values());
	}

}
