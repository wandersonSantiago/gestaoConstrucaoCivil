package br.com.app.web.controller.almoxarifado;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.entity.almoxarifado.EstoqueEmpreendimento;
import br.com.app.service.almoxarifado.EstoqueEmpreendimentoService;

@RestController
@RequestMapping("/rest/produtoEstoque")
public class EstoqueEmpreendimentoRestController {

	@Autowired
	private EstoqueEmpreendimentoService estoqueService;

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/alteraProduto")
	public void salvar(@RequestBody EstoqueEmpreendimento estoqueEmpreendimento) {
		estoqueService.salvarOuEditar(estoqueEmpreendimento);

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Collection<EstoqueEmpreendimento> buscarTodos() {
		return estoqueService.buscarTodos();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/baixo")
	public Collection<EstoqueEmpreendimento> produtoEstoqueBaixo() {
		return estoqueService.produtoEstoqueBaixo();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/alto")
	public Collection<EstoqueEmpreendimento> produtoEstoqueAlto() {
		return estoqueService.produtoEstoqueAlto();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista/paginacao")
	public Page<EstoqueEmpreendimento> lista(
			@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "0", required = false) int maxResults) {
		return estoqueService.buscarTodosComPaginacao(new PageRequest(page, maxResults));
		 
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/auditoria/entrada")
	public Page<EstoqueEmpreendimento> listaAuditoria(
			@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "0", required = false) int maxResults) {
		return estoqueService.findAll(new PageRequest(page, maxResults));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscaPorCodigo/{codigo}")
	public  EstoqueEmpreendimento buscarPorCodigo(@PathVariable String codigo) {
		return estoqueService.buscarPorCodigoOuCodigoBarraEstoque(codigo);

	}
}
