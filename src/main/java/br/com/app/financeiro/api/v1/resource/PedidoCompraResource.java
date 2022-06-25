package br.com.app.financeiro.api.v1.resource;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.financeiro.domain.model.PedidoCompra;
import br.com.app.financeiro.domain.service.PedidoCompraService;

@RestController
@RequestMapping("/rest/almoxarifado/pedidoCompra")
public class PedidoCompraResource {

	@Autowired
	private PedidoCompraService pedidoCompraService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salva")
	public void salvar(@RequestBody PedidoCompra pedidoCompra) {
		pedidoCompraService.salvaAltera(pedidoCompra);

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista")
	public Collection<PedidoCompra> buscarTodos() {

		return pedidoCompraService.buscarTodos();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}")
	public Optional<PedidoCompra> buscarPorId(@PathVariable Long id) {

		return pedidoCompraService.buscaPorId(id);
	}

}
