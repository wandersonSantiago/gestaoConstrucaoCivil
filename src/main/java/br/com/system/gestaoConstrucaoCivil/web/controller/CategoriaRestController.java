package br.com.system.gestaoConstrucaoCivil.web.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.Categoria;
import br.com.system.gestaoConstrucaoCivil.enuns.TipoCategoriaEnum;
import br.com.system.gestaoConstrucaoCivil.service.interfaceservice.Servico;

@RestController
@RequestMapping("/rest/almoxarifado/categoria")
public class CategoriaRestController {

	@Autowired
	private Servico<Categoria> categoriaService;

	@GetMapping(value = "/lista")
	public ResponseEntity<Iterable<Categoria>> buscarTodos() {
		return new ResponseEntity<Iterable<Categoria>>(categoriaService.buscarTodos(), HttpStatus.OK);
	}

	@GetMapping(value = "/buscaPorId/{id}")
	public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {

		return new ResponseEntity<Categoria>(categoriaService.buscarPorId(id), HttpStatus.OK);
	}

	@PostMapping(value = "/salva")
	public ResponseEntity<Categoria> salvar(@RequestBody @Validated Categoria categoria, BindingResult result,
			UriComponentsBuilder ucBuilder) {

		CategoriaValidator v = new CategoriaValidator();
		v.validate(categoria, result);
		categoriaService.salvarOuEditar(categoria);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				ucBuilder.path("rest/almoxarifado/categoria/salva/{id}").buildAndExpand(categoria.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@PutMapping(value = "/altera")
	public ResponseEntity<Categoria> alterar(@RequestBody Categoria categoria, UriComponentsBuilder ucBuilder) {
		categoriaService.salvarOuEditar(categoria);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("rest/almoxarifado/categoria/altera/{categoria}")
				.buildAndExpand(categoria.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@GetMapping(value = "/tipoCategoria")
	public ResponseEntity<Iterable<TipoCategoriaEnum>> tipoCategoria() {
		return new ResponseEntity<Iterable<TipoCategoriaEnum>>(Arrays.asList(TipoCategoriaEnum.values()), HttpStatus.OK);
	}

}
