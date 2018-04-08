package br.com.system.gestaoConstrucaoCivil.web.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.enuns.TipoEmpreendimentoEnum;
import br.com.system.gestaoConstrucaoCivil.service.EmpreendimentoService;

@RestController
@RequestMapping("rest/empreendimento/empreendimento")
public class EmpreendimentoRestController {

	@Autowired
	private EmpreendimentoService empreendimentoService;

	@GetMapping(value = "/lista")
	public ResponseEntity<Iterable<Empreendimento>> buscarTodos() {

		return new ResponseEntity<Iterable<Empreendimento>>(empreendimentoService.buscarTodos(), HttpStatus.OK);
	}

	@GetMapping(value = "/empreendimentoSemConfiguracaoLista")
	public ResponseEntity<Iterable<Empreendimento>> buscaEmpreendimentoSemConfiguracao() {

		return new ResponseEntity<Iterable<Empreendimento>>(empreendimentoService.buscaEmpreendimentoSemConfiguracao(),
				HttpStatus.OK);
	}

	@GetMapping(value = "/tiposEmpreendimentos")
	public ResponseEntity<Iterable<TipoEmpreendimentoEnum>> tiposEmpreendimentos() {

		return new ResponseEntity<Iterable<TipoEmpreendimentoEnum>>(Arrays.asList(TipoEmpreendimentoEnum.values()),
				HttpStatus.OK);

	}

	@GetMapping(value = "/buscaPorId/{id}")
	public ResponseEntity<Empreendimento> buscarPorId(@PathVariable Long id) {
		return new ResponseEntity<Empreendimento>(empreendimentoService.buscarPorId(id), HttpStatus.OK);
	}

	@PostMapping(value = "/salva")
	public ResponseEntity<Empreendimento> salvar(@RequestBody Empreendimento empreendimento,
			UriComponentsBuilder ucBuilder) {
		empreendimentoService.salvarOuEditar(empreendimento);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("rest/empreendimento/empreendimento/salva/{empreendimento}")
				.buildAndExpand(empreendimento.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@PutMapping(value = "/altera")
	public ResponseEntity<Empreendimento> alterar(@RequestBody Empreendimento empreendimento,
			UriComponentsBuilder ucBuilder) {
		empreendimentoService.salvarOuEditar(empreendimento);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("rest/empreendimento/empreendimento/altera/{empreendimento}")
				.buildAndExpand(empreendimento.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
}
