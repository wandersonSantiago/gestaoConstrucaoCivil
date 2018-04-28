package br.com.app.web.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

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

import br.com.app.entity.Permissao;
import br.com.app.enuns.TipoModulo;
import br.com.app.service.PermissaoService;

@RestController
@RequestMapping("/rest/usuario/permissao")
public class PermissaoRestController {

	@Autowired
	private PermissaoService permissaoService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void insert(@RequestBody Permissao permissao) {
		permissaoService.insert(permissao);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping
	public void alterar(@RequestBody Permissao permissao) {
		permissaoService.insert(permissao);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}")
	public Optional<Permissao> buscarPorId(@PathVariable Long id) {
		return permissaoService.buscarPorId(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Collection<Permissao> buscarUsuarios() {
		return permissaoService.lista();

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/modulo")
	public Collection<TipoModulo> uf() {
		return Arrays.asList(TipoModulo.values());

	}

}
