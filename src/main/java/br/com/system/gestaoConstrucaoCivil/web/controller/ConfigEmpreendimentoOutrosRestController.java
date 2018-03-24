package br.com.system.gestaoConstrucaoCivil.web.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/listaOutros")
	public Collection<ConfigEmpreendimentoOutros> buscarTodos() {

		return configEmpreeendimentoOutrosService.buscarTodos();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscaOutrosPorId/{id}")
	public ConfigEmpreendimentoOutros buscarPorId(@PathVariable Long id) {
		return configEmpreeendimentoOutrosService.buscarPorId(id);
	}

}
