package br.com.system.gestaoConstrucaoCivil.web.controller.almoxarifado;

import java.util.Collection;

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

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoCasa;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.RequisicaoCasaService;

@RestController
@RequestMapping("/rest/almoxarifado/estoque/requisicaoCasa")
public class RequisicaoCasaRestController {

	@Autowired
	private RequisicaoCasaService requisicaoCasaService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salva")
	public void salvarOuEditar(@RequestBody RequisicaoCasa requisicao) {
		requisicaoCasaService.salvarOuEditar(requisicao);

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista")
	public Collection<RequisicaoCasa> buscarTodos() {
		return requisicaoCasaService.buscarTodos();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista/paginacao")
	public Page<RequisicaoCasa> lista(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "0", required = false) int maxResults) {
		return requisicaoCasaService.buscarTodosComPaginacao(new PageRequest(page, maxResults));
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/aceitar")
	public void aceitar(@RequestBody Integer numeroRequisicao) {
		requisicaoCasaService.aceitar(numeroRequisicao);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/rejeitar")
	public void rejeitar(@RequestBody Integer numeroRequisicao) {
		requisicaoCasaService.rejeitar(numeroRequisicao);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping(value = "/buscaPorId/{id}")
	public RequisicaoCasa buscarPorId(@PathVariable Long id) {
		return  requisicaoCasaService.buscarPorId(id);
	}
}
