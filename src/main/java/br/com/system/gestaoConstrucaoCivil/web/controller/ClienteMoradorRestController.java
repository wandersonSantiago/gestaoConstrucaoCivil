package br.com.system.gestaoConstrucaoCivil.web.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.gestaoConstrucaoCivil.entity.ClienteMorador;
import br.com.system.gestaoConstrucaoCivil.entity.ClienteMoradorCasa;
import br.com.system.gestaoConstrucaoCivil.entity.ClienteMoradorEdificio;
import br.com.system.gestaoConstrucaoCivil.service.ClienteMoradorService;

@RestController
@RequestMapping("/rest/morador")
public class ClienteMoradorRestController {

	@Autowired
	private ClienteMoradorService clienteMoradorService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista")
	public Collection<ClienteMorador> buscarTodos() {
		return clienteMoradorService.lista();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Page<ClienteMorador> lista(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "0", required = false) int maxResults) {
		return clienteMoradorService.buscarTodos(new PageRequest(page, maxResults));

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/edificio")
	public void salvarEdificio(@RequestBody ClienteMoradorEdificio clienteMoradorEdificio) {
		clienteMoradorService.salvarOuEditar(clienteMoradorEdificio);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/casa")
	public void salvarCasa(@RequestBody ClienteMoradorCasa clienteMorador) {
		clienteMoradorService.salvarOuEditar(clienteMorador);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/edificio")
	public void alterarEdificio(@RequestBody ClienteMoradorEdificio clienteMoradorEdificio) {
		clienteMoradorService.salvarOuEditar(clienteMoradorEdificio);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/casa")
	public void alterarCasa(@RequestBody ClienteMoradorCasa clienteMorador) {
		clienteMoradorService.salvarOuEditar(clienteMorador);

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}")
	public Optional<ClienteMorador> buscarPorId(@PathVariable Long id) {
		return clienteMoradorService.buscarPorId(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{cpf}/cpf")
	public ClienteMorador buscarPorCpf(@PathVariable String cpf) {
		return clienteMoradorService.buscarPorCpf(cpf);
	}

}
