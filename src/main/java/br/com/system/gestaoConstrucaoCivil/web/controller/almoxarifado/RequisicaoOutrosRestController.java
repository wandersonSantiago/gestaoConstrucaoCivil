package br.com.system.gestaoConstrucaoCivil.web.controller.almoxarifado;

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

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoOutros;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.RequisicaoOutrosService;

@RestController
@RequestMapping("/rest/almoxarifado/estoque/requisicaoOutros")
public class RequisicaoOutrosRestController {

	@Autowired
	private RequisicaoOutrosService requisicaoOutrosService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salva")
	public void salvarOuEditar(@RequestBody RequisicaoOutros requisicao) {
		requisicaoOutrosService.salvarOuEditar(requisicao);

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista")
	public Collection<RequisicaoOutros> buscarTodos() {
		return requisicaoOutrosService.buscarTodos();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista/paginacao")
	public Page<RequisicaoOutros> lista(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "0", required = false) int maxResults) {
		return requisicaoOutrosService.buscarTodosComPaginacao(new PageRequest(page, maxResults));

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/aceitar")
	public void aceitar(@RequestBody Integer numeroRequisicao) {
		requisicaoOutrosService.aceitar(numeroRequisicao);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/rejeitar")
	public void rejeitar(@RequestBody Integer numeroRequisicao) {
		requisicaoOutrosService.rejeitar(numeroRequisicao);

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscaPorId/{id}")
	public Optional<RequisicaoOutros> buscarPorId(@PathVariable Long id) {
		return requisicaoOutrosService.buscarPorId(id);
	}
}
