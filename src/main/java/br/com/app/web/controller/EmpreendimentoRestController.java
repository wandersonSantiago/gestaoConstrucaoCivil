package br.com.app.web.controller;

import java.util.Arrays;
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

import br.com.app.entity.Empreendimento;
import br.com.app.enuns.TipoEmpreendimentoEnum;
import br.com.app.service.EmpreendimentoService;

@RestController
@RequestMapping("rest/empreendimento/empreendimento")
public class EmpreendimentoRestController {

	@Autowired
	private EmpreendimentoService empreendimentoService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista")
	public Collection<Empreendimento> buscarTodos() {

		return empreendimentoService.buscarTodos();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/empreendimentoSemConfiguracaoLista")
	public Collection<Empreendimento> buscaEmpreendimentoSemConfiguracao() {

		return empreendimentoService.buscaEmpreendimentoSemConfiguracao();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/tiposEmpreendimentos")
	public Collection<TipoEmpreendimentoEnum> tiposEmpreendimentos() {

		return Arrays.asList(TipoEmpreendimentoEnum.values());

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscaPorId/{id}")
	public Optional<Empreendimento> buscarPorId(@PathVariable Long id) {
		return empreendimentoService.buscarPorId(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salva")
	public void salvar(@RequestBody Empreendimento empreendimento){
		empreendimentoService.salvarOuEditar(empreendimento);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/altera")
	public void alterar(@RequestBody Empreendimento empreendimento) {
		empreendimentoService.salvarOuEditar(empreendimento);
	}
}
