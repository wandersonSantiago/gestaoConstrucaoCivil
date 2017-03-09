package br.com.system.gestaoConstrucaoCivil.web.controller.almoxarifado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.TipoProduto;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.TipoProdutoService;

@RestController
@RequestMapping(value = "/rest/almoxarifado/produto/tipo")
public class TipoProdutoRestController {

	@Autowired
	private TipoProdutoService tipoProdutoService;

	@PreAuthorize("hasAnyRole('ROLE_MODULO_ADMIN','ROLE_MODULO_CADASTROS_PRODUTO_TIPO_CONSULTAR')")
	@GetMapping(value = "/lista")
	public ResponseEntity<Iterable<TipoProduto>> buscarTodos() {
		return new ResponseEntity<Iterable<TipoProduto>>(tipoProdutoService.buscarTodos(), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_MODULO_ADMIN','ROLE_MODULO_CADASTROS_PRODUTO_TIPO_CONSULTAR')")
	@GetMapping(value = "/buscaPorId/{id}")
	public ResponseEntity<TipoProduto> buscarPorId(@PathVariable Long id) {
		return new ResponseEntity<TipoProduto>(tipoProdutoService.buscaPorId(id), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_MODULO_ADMIN','ROLE_MODULO_CADASTROS_PRODUTO_TIPO_CADASTRAR')")
	@PostMapping(value = "/salva")
	public ResponseEntity<TipoProduto> salvar(@RequestBody TipoProduto tipoProduto, UriComponentsBuilder ucBuilder) {
		tipoProdutoService.salvaAltera(tipoProduto);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/rest/almoxarifado/produto/tipo/salva/{id}")
				.buildAndExpand(tipoProduto.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@PreAuthorize("hasAnyRole('ROLE_MODULO_ADMIN','ROLE_MODULO_CADASTROS_PRODUTO_TIPO_ALTERAR')")
	@PutMapping(value = "/altera")
	public ResponseEntity<TipoProduto> alterar(@RequestBody TipoProduto tipoProduto, UriComponentsBuilder ucBuilder) {
		tipoProdutoService.salvaAltera(tipoProduto);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/rest/almoxarifado/produto/tipo/altera/{id}")
				.buildAndExpand(tipoProduto.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

}
