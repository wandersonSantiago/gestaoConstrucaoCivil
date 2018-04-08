package br.com.system.gestaoConstrucaoCivil.web.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.gestaoConstrucaoCivil.entity.PermissaoUsuario;
import br.com.system.gestaoConstrucaoCivil.service.PermissaoUsuarioService;

@RestController
@RequestMapping("/rest/permissao/permissaoUsuario")
public class PermissaoUsuarioRestController {

	@Autowired
	private PermissaoUsuarioService permissaoService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salva")
	public void salva(@RequestBody PermissaoUsuario permissao) {
		permissaoService.salvarOuEditar(permissao);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscaPorUsuario/{id}")
	public Collection<PermissaoUsuario> buscarPorIdUsuario(@PathVariable Long id) {
		return permissaoService.buscarPorIdUsuario(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "/removerPermissao/{id}")
	public void removerPermissao(@PathVariable Long id) {
		permissaoService.removerPermissao(id);

	}
}
