package br.com.system.gestaoConstrucaoCivil.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.gestaoConstrucaoCivil.entity.PermissaoUsuario;
import br.com.system.gestaoConstrucaoCivil.service.PermissaoUsuarioService;

@RestController
@RequestMapping("/rest/permissao/permissaoUsuario")
public class PermissaoUsuarioRestController {

	@Autowired
	private PermissaoUsuarioService permissaoService;
	
	@GetMapping(value = "/buscaPorUsuario/{id}")
	public ResponseEntity<Iterable<PermissaoUsuario>> buscarPorIdUsuario(@PathVariable Long id)
	{
		return new ResponseEntity<Iterable<PermissaoUsuario>>(permissaoService.buscarPorIdUsuario(id), HttpStatus.OK);
	}
	@DeleteMapping(value = "/removerPermissao")
	public ResponseEntity<PermissaoUsuario> removerPermissao(@RequestBody PermissaoUsuario permissao)
	{
		permissaoService.removerPermissao(permissao);
		return new ResponseEntity<PermissaoUsuario>(HttpStatus.OK);
	}
}
