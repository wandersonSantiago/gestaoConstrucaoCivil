package br.com.app.web.controller.almoxarifado;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.entity.almoxarifado.TipoProduto;
import br.com.app.service.almoxarifado.TipoProdutoService;

@RestController
@RequestMapping(value = "/rest/almoxarifado/produto/tipo")
public class TipoProdutoRestController {

	@Autowired
	private TipoProdutoService tipoProdutoService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista")
	public Collection<TipoProduto> buscarTodos() {
		return tipoProdutoService.buscarTodos();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscaPorId/{id}")
	public Optional<TipoProduto> buscarPorId(@PathVariable Long id) {
		return tipoProdutoService.buscaPorId(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salva")
	public void salvar(@RequestBody TipoProduto tipoProduto) {
		tipoProdutoService.salvaAltera(tipoProduto);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/altera")
	public void alterar(@RequestBody TipoProduto tipoProduto) {
		tipoProdutoService.salvaAltera(tipoProduto);

	}

}
