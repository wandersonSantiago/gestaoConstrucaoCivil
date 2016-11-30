package br.com.system.gestaoConstrucaoCivil.web.controller;

import java.util.List;

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

import br.com.system.gestaoConstrucaoCivil.entity.ConfigEmpreendimentoOutros;
import br.com.system.gestaoConstrucaoCivil.service.ConfigEmpreendimentoOutrosService;

@RestController
@RequestMapping("/rest/empreendimento/configuracao")
public class ConfigEmpreendimentoOutrosRestController {

	@Autowired
	private ConfigEmpreendimentoOutrosService configEmpreeendimentoOutrosService;

	@RequestMapping(value = "/salvaOutros", method = RequestMethod.POST)
	public ResponseEntity salvar(@RequestBody List<ConfigEmpreendimentoOutros> configEmpreendimentoOutros,
			UriComponentsBuilder ucBuilder) {
		configEmpreeendimentoOutrosService.salvarOuEditar(configEmpreendimentoOutros);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/listaOutros")
	public ResponseEntity<Iterable<ConfigEmpreendimentoOutros>> buscarTodos() {
		Iterable<ConfigEmpreendimentoOutros> configEmpreendimentoOutros = configEmpreeendimentoOutrosService
				.buscarTodos();
		return new ResponseEntity<Iterable<ConfigEmpreendimentoOutros>>(configEmpreendimentoOutros, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscaOutrosPorId/{id}", method = RequestMethod.GET)
	public ResponseEntity<ConfigEmpreendimentoOutros> buscarPorId(@PathVariable Long id) {
		return new ResponseEntity<ConfigEmpreendimentoOutros>(configEmpreeendimentoOutrosService.buscarPorId(id),
				HttpStatus.OK);
	}

}
