package br.com.system.gestaoConstrucaoCivil.web.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.system.gestaoConstrucaoCivil.entity.Usuario;
import br.com.system.gestaoConstrucaoCivil.enuns.PerfilUsuarioEnum;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;
import br.com.system.gestaoConstrucaoCivil.service.UsuarioService;

@RestController
@RequestMapping("/rest/usuario")
public class UsuarioRestController {

	@Autowired
	private UsuarioService usuarioService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/usuario")
	@ResponseBody
	public Principal user(Principal user, HttpSession session) {

		return user;
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista")
	public Collection<Usuario> buscarUsuarios() {

		return usuarioService.buscarTodos();

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salva")
	public void salva(@RequestBody Usuario usuario) {
		usuarioService.salvarOuEditar(usuario);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/altera")
	public void alterar(@RequestBody Usuario usuario) {
		usuarioService.salvarOuEditar(usuario);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscaPorId/{id}")
	public Optional<Usuario> buscarPorId(@PathVariable Long id) {

		return usuarioService.buscarUsuarioPorId(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/existeLogin/{login}")
	public Boolean verificarSeExisteLogin(@PathVariable String login) {
		return usuarioService.existeLoginCadastrado(login);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/perfil")
	public Collection<PerfilUsuarioEnum> uf() {

		return Arrays.asList(PerfilUsuarioEnum.values());

	}

	@GetMapping("/usuarios")
	public ResponseEntity<?> user(SessionUsuario user, HttpSession session) {

		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
