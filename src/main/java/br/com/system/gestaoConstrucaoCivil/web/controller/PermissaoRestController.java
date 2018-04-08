package br.com.system.gestaoConstrucaoCivil.web.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.Permissao;
import br.com.system.gestaoConstrucaoCivil.enuns.TipoModulo;
import br.com.system.gestaoConstrucaoCivil.service.PermissaoService;

@RestController
@RequestMapping("/rest/usuario/permissao")
public class PermissaoRestController {

	@Autowired
	private PermissaoService permissaoService;
	
	@PostMapping
	public ResponseEntity<Permissao> salva(@RequestBody Permissao permissao, UriComponentsBuilder ucBuilder) {
		permissaoService.salvar(permissao);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Permissao>(headers, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Permissao> alterar(@RequestBody Permissao permissao, UriComponentsBuilder ucBuilder) {
		permissaoService.salvar(permissao);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Permissao>(headers, HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Permissao> buscarPorId(@PathVariable Long id) {
		return new ResponseEntity<Permissao>(permissaoService.buscarPorId(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/lista")
	public ResponseEntity<Iterable<Permissao>> buscarUsuarios() {

		Iterable<Permissao> permissao = permissaoService.lista();
		return new ResponseEntity<Iterable<Permissao>>(permissao, HttpStatus.OK);
	}
	
	@GetMapping(value = "/modulo")
	public ResponseEntity<Iterable<TipoModulo>> uf() {
		return new ResponseEntity<Iterable<TipoModulo>>(Arrays.asList(TipoModulo.values()),
				HttpStatus.OK);
	}

	

}
