package br.com.app.web.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.entity.Morador;
import br.com.app.service.MoradorService;

@RestController
@RequestMapping("/rest/morador")
public class MoradorRestController {

	@Autowired
	private MoradorService clienteMoradorService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista")
	public Collection<Morador> buscarTodos() {
		return clienteMoradorService.lista();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Page<Morador> lista(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "0", required = false) int maxResults) {
		return clienteMoradorService.buscarTodos(new PageRequest(page, maxResults));

	}
 
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}")
	public Optional<Morador> buscarPorId(@PathVariable Long id) {
		return clienteMoradorService.buscarPorId(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{cpf}/cpf")
	public Morador buscarPorCpf(@PathVariable String cpf) {
		return clienteMoradorService.buscarPorCpf(cpf);
	}

}
