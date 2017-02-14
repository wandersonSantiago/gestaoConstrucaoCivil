package br.com.system.gestaoConstrucaoCivil.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.PermissaoUsuario;
import br.com.system.gestaoConstrucaoCivil.service.PermissaoUsuarioService;

@RestController
@RequestMapping("/rest/permissao/permissaoUsuario")
public class PermissaoUsuarioRestController {

	@Autowired
	private PermissaoUsuarioService permissaoService;
	
	@PostMapping(value = "/salva")
	public ResponseEntity<PermissaoUsuario> salva(@RequestBody PermissaoUsuario permissao, UriComponentsBuilder ucBuilder) {
		permissaoService.salvarOuEditar(permissao);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<PermissaoUsuario>(headers, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/buscaPorUsuario/{id}")
	public ResponseEntity<Iterable<PermissaoUsuario>> buscarPorIdUsuario(@PathVariable Long id)
	{
		return new ResponseEntity<Iterable<PermissaoUsuario>>(permissaoService.buscarPorIdUsuario(id), HttpStatus.OK);
	}
	@DeleteMapping(value = "/removerPermissao/{id}")
	public ResponseEntity<PermissaoUsuario> removerPermissao(@PathVariable Long id)
	{
		permissaoService.removerPermissao(id);
		return new ResponseEntity<PermissaoUsuario>(HttpStatus.OK);
	}
}
