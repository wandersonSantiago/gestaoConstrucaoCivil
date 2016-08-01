package br.com.system.gestaoConstrucaoCivil.web.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.enuns.TipoEmpreendimentoEnum;
import br.com.system.gestaoConstrucaoCivil.service.EmpreendimentoService;
import br.com.system.gestaoConstrucaoCivil.service.EnderecoService;

@RestController
@RequestMapping("rest/empreendimento/cadastrarEmpreendimento")
public class EmpreendimentoRestController {

	@Autowired
	private EmpreendimentoService empreendimentoService;
	@Autowired
	private EnderecoService enderecoService;

	@RequestMapping(method = RequestMethod.GET, value = "/listaEmpreendimento")
	public ResponseEntity<Iterable<Empreendimento>> empreendimentos() {
		Iterable<Empreendimento> empreendimento = empreendimentoService.buscarTodos();
		return new ResponseEntity<Iterable<Empreendimento>>(empreendimento, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/tiposEmpreendimentos")
	public ResponseEntity<Iterable<TipoEmpreendimentoEnum>> tiposEmpreendimentos() {

		Iterable<TipoEmpreendimentoEnum> tiposEmpreendimentos = Arrays.asList(TipoEmpreendimentoEnum.values());
		return new ResponseEntity<Iterable<TipoEmpreendimentoEnum>>(tiposEmpreendimentos, HttpStatus.OK);

	}

	@RequestMapping(value = "/listarEmpreendimentoId/{id}", method = RequestMethod.GET)
	public ResponseEntity<Empreendimento> buscarEmpreendimentoPorId(@PathVariable Long id) {
		return new ResponseEntity<Empreendimento>(empreendimentoService.buscarPorId(id), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity salvarEmpreendimento(@RequestBody Empreendimento empreendimento,
			UriComponentsBuilder ucBuilder) {

		empreendimentoService.salvarOuEditar(empreendimento);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("rest/empreendimento/cadastrarEmpreendimento/{empreendimento}")
				.buildAndExpand(empreendimento.getId()).toUri());

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/alterarEmpreendimento", method = RequestMethod.PUT)
	public ResponseEntity alterarEmpreendimento(@RequestBody Empreendimento empreendimento,
			UriComponentsBuilder ucBuilder) {
		empreendimentoService.salvarOuEditar(empreendimento);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("rest/empreendimento/alterarEmpreendimento/{empreendimento}")
				.buildAndExpand(empreendimento.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
}
