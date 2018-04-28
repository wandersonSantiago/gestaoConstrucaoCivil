package br.com.app.web.controller.almoxarifado;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.entity.almoxarifado.Produto;
import br.com.app.service.almoxarifado.ProdutoService;

@RestController
@RequestMapping("/rest/almoxarifado/produto")
public class ProdutoRestController {

	@Autowired
	private ProdutoService produtoService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista")
	public Collection<Produto> buscarTodos() {

		return produtoService.buscarTodos();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<Produto> lista(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "0", required = false) int maxResults) {
		return produtoService.buscarTodosComPaginacao(new PageRequest(page, maxResults));

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscaPorId/{id}")
	public Optional<Produto> buscarPorId(@PathVariable Long id) {

		return produtoService.buscaPorId(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscaPorCodigo/{codigo}")
	public Produto buscarPorCodigo(@PathVariable String codigo) {
		return produtoService.buscarPorCodigoOuCodigoBarra(codigo);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salva")
	public void salvar(@RequestBody Produto produto) {
		produtoService.salvarOuEditar(produto);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/altera")
	public void alterar(@RequestBody Produto produto) {
		produtoService.salvarOuEditar(produto);
	}
}
