package br.com.system.gestaoConstrucaoCivil.web.controller;

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

import br.com.system.gestaoConstrucaoCivil.entity.ConfigEmpreendimentoEdificio;
import br.com.system.gestaoConstrucaoCivil.pojo.ConfigEmpreendimentoEdificioPojo;
import br.com.system.gestaoConstrucaoCivil.service.ConfigEmpreendimentoEdificioService;

@RestController
@RequestMapping("/rest/empreendimento/configuracao")
public class ConfigEmpreendimentoEdificioRestController {

	@Autowired
	private ConfigEmpreendimentoEdificioService configEmpreeendimentoEdificioService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salvaEdificio")
	public void salvar(@RequestBody ConfigEmpreendimentoEdificio configEmpreendimentoEdificio) {

		configEmpreeendimentoEdificioService.salvarOuEditar(configEmpreendimentoEdificio);
	 
	}

	@GetMapping(value = "/listaEdificio/{id}")
	public ResponseEntity<ConfigEmpreendimentoEdificio> buscarPorId(@PathVariable Long id) {
		return new ResponseEntity<ConfigEmpreendimentoEdificio>(configEmpreeendimentoEdificioService.buscarPorId(id),
				HttpStatus.OK);
	}

	@GetMapping(value = "/quantidadeEdificio")
	public ResponseEntity<ConfigEmpreendimentoEdificioPojo> configPojo() {

		return new ResponseEntity<ConfigEmpreendimentoEdificioPojo>(configEmpreeendimentoEdificioService.getConfig(),
				HttpStatus.OK);
	}
}
