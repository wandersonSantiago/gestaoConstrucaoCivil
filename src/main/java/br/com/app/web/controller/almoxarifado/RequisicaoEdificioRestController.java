package br.com.app.web.controller.almoxarifado;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.entity.almoxarifado.RequisicaoEdificio;
import br.com.app.service.almoxarifado.RequisicaoEdificioService;

@RestController
@RequestMapping("/rest/almoxarifado/estoque/requisicaoEdificio")
public class RequisicaoEdificioRestController {

	@Autowired
	private RequisicaoEdificioService requisicaoEdificioService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salva")
	public void salvarOuEditar(@RequestBody RequisicaoEdificio requisicao) {
		requisicaoEdificioService.salvarOuEditar(requisicao);

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista")
	public Collection<RequisicaoEdificio> buscarTodos() {
		return requisicaoEdificioService.buscarTodos();

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista/paginacao")
	public Page<RequisicaoEdificio> lista(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "0", required = false) int maxResults) {

		return requisicaoEdificioService.buscarTodosComPaginacao(new PageRequest(page, maxResults));

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/aceitar")
	public void aceitar(@RequestBody Integer numeroRequisicao) {
		requisicaoEdificioService.aceitar(numeroRequisicao);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/rejeitar")
	public void rejeitar(@RequestBody Integer numeroRequisicao) {
		requisicaoEdificioService.rejeitar(numeroRequisicao);

	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/buscaPorId/{id}")
	public Optional<RequisicaoEdificio> buscarPorId(@PathVariable Long id) {
		return requisicaoEdificioService.buscarPorId(id);
	}
}
