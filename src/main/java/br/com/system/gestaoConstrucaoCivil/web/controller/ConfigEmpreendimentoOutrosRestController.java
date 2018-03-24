package br.com.system.gestaoConstrucaoCivil.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.gestaoConstrucaoCivil.entity.ConfigEmpreendimentoOutros;
import br.com.system.gestaoConstrucaoCivil.service.ConfigEmpreendimentoOutrosService;

@RestController
@RequestMapping("/rest/empreendimento/configuracao")
public class ConfigEmpreendimentoOutrosRestController {

	@Autowired
	private ConfigEmpreendimentoOutrosService configEmpreeendimentoOutrosService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salvaOutros")
	public void salvar(@RequestBody List<ConfigEmpreendimentoOutros> configEmpreendimentoOutros) {
		configEmpreeendimentoOutrosService.salvarOuEditar(configEmpreendimentoOutros);
		
	}

	@GetMapping(value = "/listaOutros")
	public ResponseEntity<Iterable<ConfigEmpreendimentoOutros>> buscarTodos() {
		
		return new ResponseEntity<Iterable<ConfigEmpreendimentoOutros>>(configEmpreeendimentoOutrosService.buscarTodos(), HttpStatus.OK);
	}

	@GetMapping(value = "/buscaOutrosPorId/{id}")
	public ResponseEntity<ConfigEmpreendimentoOutros> buscarPorId(@PathVariable Long id) {
		return new ResponseEntity<ConfigEmpreendimentoOutros>(configEmpreeendimentoOutrosService.buscarPorId(id),
				HttpStatus.OK);
	}

}
