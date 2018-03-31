package br.com.system.gestaoConstrucaoCivil.web.controller.almoxarifado;

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

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Cotacao;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.CotacaoService;

@RestController
@RequestMapping("/rest/almoxarifado/cotacao")
public class CotacaoRestController {

	@Autowired
	private CotacaoService cotacaoService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salva")
	public void salvar(@RequestBody Cotacao cotacao) {
		cotacaoService.salvaAltera(cotacao);

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista")
	public Collection<Cotacao> buscarTodos() {
		return cotacaoService.buscarTodos();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscaPorId/{id}")
	public Optional<Cotacao> buscarPorId(@PathVariable Long id) {
		return cotacaoService.buscaPorId(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/fecharCotacao/{idCotacao}")
	public void fecharCotacao(@PathVariable Long idCotacao) {
		cotacaoService.fecharCotacao(idCotacao);

	}

}
