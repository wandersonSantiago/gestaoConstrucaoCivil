package br.com.system.gestaoConstrucaoCivil.web.controller;

import java.security.Principal;
import java.util.Arrays;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.Usuario;
import br.com.system.gestaoConstrucaoCivil.enuns.PerfilUsuarioEnum;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;
import br.com.system.gestaoConstrucaoCivil.service.UsuarioService;

@RestController
@RequestMapping("/rest/usuario")
public class UsuarioRestController {

	@Autowired
	private UsuarioService usuarioService;
	 
	
	@GetMapping(value = "/usuario")
	@ResponseBody
	public Principal user(Principal user, HttpSession session) {

		return user;
	}

	@GetMapping(value = "/lista")
	public ResponseEntity<Iterable<Usuario>> buscarUsuarios() {

		Iterable<Usuario> usuario = usuarioService.buscarTodos();
		return new ResponseEntity<Iterable<Usuario>>(usuario, HttpStatus.OK);
	}

	@PostMapping(value = "/salva")
	public ResponseEntity<Usuario> salva(@RequestBody Usuario usuario, UriComponentsBuilder ucBuilder) {
		usuarioService.salvarOuEditar(usuario);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/rest/usuario/salva/{id}").buildAndExpand(usuario.getId()).toUri());
		return new ResponseEntity<Usuario>(headers, HttpStatus.CREATED);
	}

	@PutMapping(value = "/altera")
	public ResponseEntity<Usuario> alterar(@RequestBody Usuario usuario, UriComponentsBuilder ucBuilder) {
		usuarioService.salvarOuEditar(usuario);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/rest/usuario/altera/{id}").buildAndExpand(usuario.getId()).toUri());
		return new ResponseEntity<Usuario>(headers, HttpStatus.CREATED);
	}

	@GetMapping(value = "/buscaPorId/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
		
	 	
		return new ResponseEntity<Usuario>(usuarioService.buscarUsuarioPorId(id), HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/existeLogin/{login}")
	public Boolean verificarSeExisteLogin(@PathVariable String login) {
		return usuarioService.existeLoginCadastrado(login);
	}

	@GetMapping(value = "/perfil")
	public ResponseEntity<Iterable<PerfilUsuarioEnum>> uf() {

		return new ResponseEntity<Iterable<PerfilUsuarioEnum>>(Arrays.asList(PerfilUsuarioEnum.values()),
				HttpStatus.OK);
	}

	@GetMapping("/usuarios")
	public ResponseEntity<?> user(SessionUsuario user, HttpSession session) {

		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
