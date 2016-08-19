package br.com.system.gestaoConstrucaoCivil.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.system.gestaoConstrucaoCivil.entity.Usuario;
import br.com.system.gestaoConstrucaoCivil.service.UsuarioService;

@RestController
@RequestMapping("/rest/usuario")
public class UsuarioRestController {

	@Autowired
	 private UsuarioService usuarioService;

	
	
	@RequestMapping(value="/usuario")
	@ResponseBody
	public Principal user(Principal user) {
		return user;
	}
	
	
	
	 @RequestMapping(method = RequestMethod.GET, value="/listarUsuario")
	 public ResponseEntity<Iterable<Usuario>> buscarUsuarios() {	  
	  System.out.println("lista ok");
	  Iterable<Usuario> usuario = usuarioService.buscarTodos();
	  return new ResponseEntity<Iterable<Usuario>>(usuario, HttpStatus.OK);
	 }
	 
	 
	 @RequestMapping(method = RequestMethod.POST, value="/cadastrarUsuario")
	 public ResponseEntity salva(@RequestBody Usuario usuario,UriComponentsBuilder ucBuilder){
		 usuarioService.salvarOuEditar(usuario);
		 HttpHeaders headers =new HttpHeaders();
		 headers.setLocation(ucBuilder.path("/rest/usuario/cadastrarUsuario/{id}").buildAndExpand(usuario.getId()).toUri());
		 return new ResponseEntity(headers, HttpStatus.CREATED);
	 }

}
