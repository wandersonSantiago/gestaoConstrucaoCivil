package br.com.app.web.controller;

import java.util.Arrays;
import java.util.List;

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

import br.com.app.entity.ConfiguracaoEmpreendimento;
import br.com.app.enuns.DataBaseEnum;
import br.com.app.service.ConfiguracaoEmpreendimentoService;

@RestController
@RequestMapping("/rest/configuracao/empreendimento")
public class ConfiguracaoEmpreendimentoRestController {

	@Autowired
	private ConfiguracaoEmpreendimentoService configuracaoEmpreendimentoService;

			
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public ConfiguracaoEmpreendimento insert(@RequestBody ConfiguracaoEmpreendimento config) {
		configuracaoEmpreendimentoService.insert(config);
		return config;
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping
	public void update(@RequestBody ConfiguracaoEmpreendimento config) {
		configuracaoEmpreendimentoService.update(config);
	}
		
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public ConfiguracaoEmpreendimento findByEmpreendimentoId(@PathVariable Long id) {
		return configuracaoEmpreendimentoService.findByEmpreendimentoId(id);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/dataBase")
	public List<DataBaseEnum> dataBase() {
		return Arrays.asList(DataBaseEnum.values());
	}
}
