package br.com.app.web.controller.almoxarifado;

import java.util.Arrays;
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

import br.com.app.entity.almoxarifado.Cotacao;
import br.com.app.enuns.StatusCotacao;
import br.com.app.enuns.UnidadeMedidaEnum;
import br.com.app.repository.filter.CotacaoFilter;
import br.com.app.service.almoxarifado.CotacaoService;

@RestController
@RequestMapping("/rest/almoxarifado/cotacao")
public class CotacaoRestController {

	@Autowired
	private CotacaoService cotacaoService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void salvar(@RequestBody Cotacao cotacao) {
		cotacaoService.salvaAltera(cotacao);

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}")
	public Optional<Cotacao> buscarPorId(@PathVariable Long id) {
		return cotacaoService.buscaPorId(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/close/{idCotacao}")
	public void fecharCotacao(@PathVariable Long idCotacao) {
		cotacaoService.fecharCotacao(idCotacao);

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/status")
	public Collection<StatusCotacao> status() {
		return Arrays.asList(StatusCotacao.values());
	}
	
	@GetMapping(value = "/filtro")
	public ResponseEntity<Page<Cotacao>> filtro(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="tema") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction,
			@RequestParam(value="cotacaoFilter", required = true )CotacaoFilter filter) {

		Page<Cotacao> list = cotacaoService.filter(filter, PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/buscar")
	public ResponseEntity<Page<Cotacao>> findByDescricao(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="tema") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction,
			@RequestParam(value="tema", required = false , defaultValue="")String tema) {

		Page<Cotacao> list = null;
		
		if(tema.isEmpty() || tema.equalsIgnoreCase("")) {
			list = cotacaoService.findAll(PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}else {
			list = cotacaoService.findByTemaIgnoreCase(tema, PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}  
		
		return ResponseEntity.ok().body(list);
	}
}
