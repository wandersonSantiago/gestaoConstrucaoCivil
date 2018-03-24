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

import br.com.system.gestaoConstrucaoCivil.entity.ConfigEmpreendimentoCasa;
import br.com.system.gestaoConstrucaoCivil.pojo.ConfigEmpreendimentoCasaPojo;
import br.com.system.gestaoConstrucaoCivil.service.ConfigEmpreendimentoCasaService;

@RestController
@RequestMapping("/rest/empreendimento/configuracao")
public class ConfigEmpreendimentoCasaRestController {

	@Autowired
	private ConfigEmpreendimentoCasaService configEmpreeendimentoCasaService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salvaCasa")
	public void salvar(@RequestBody ConfigEmpreendimentoCasa configEmpreendimentoCasa) {

		configEmpreeendimentoCasaService.salvarOuEditar(configEmpreendimentoCasa);

	}

	@GetMapping(value = "/listaCasa/{id}")
	public ResponseEntity<ConfigEmpreendimentoCasa> buscarPorId(@PathVariable Long id) {
		return new ResponseEntity<ConfigEmpreendimentoCasa>(configEmpreeendimentoCasaService.buscarPorId(id),
				HttpStatus.OK);
	}

	@GetMapping(value = "/quantidadeCasa")
	public ResponseEntity<ConfigEmpreendimentoCasaPojo> configPojo() {

		return new ResponseEntity<ConfigEmpreendimentoCasaPojo>(configEmpreeendimentoCasaService.getConfig(),
				HttpStatus.OK);
	}
}
