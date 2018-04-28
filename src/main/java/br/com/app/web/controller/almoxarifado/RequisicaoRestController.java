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

import br.com.app.entity.almoxarifado.Requisicao;
import br.com.app.service.almoxarifado.RequisicaoService;

@RestController
@RequestMapping("/rest/almoxarifado/estoque/requisicao")
public class RequisicaoRestController {

	@Autowired
	private RequisicaoService requisicaoService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salva")
	public void insert(@RequestBody Requisicao requisicao) {
		requisicaoService.insert(requisicao);

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista")
	public Collection<Requisicao> buscarTodos() {
		return requisicaoService.buscarTodos();

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista/paginacao")
	public Page<Requisicao> lista(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "0", required = false) int maxResults) {

		return requisicaoService.buscarTodosComPaginacao(new PageRequest(page, maxResults));

	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/aceitar")
	public void aceitar(@RequestBody Integer numeroRequisicao) {
		requisicaoService.aceitar(numeroRequisicao);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/rejeitar")
	public void rejeitar(@RequestBody Integer numeroRequisicao) {
		 
		requisicaoService.rejeitar(numeroRequisicao);

	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/buscaPorId/{id}")
	public Optional<Requisicao> buscarPorId(@PathVariable Long id) {
		return requisicaoService.buscarPorId(id);
	}

}
