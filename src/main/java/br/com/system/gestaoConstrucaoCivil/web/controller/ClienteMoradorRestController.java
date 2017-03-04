package br.com.system.gestaoConstrucaoCivil.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.ClienteMorador;
import br.com.system.gestaoConstrucaoCivil.entity.ClienteMoradorCasa;
import br.com.system.gestaoConstrucaoCivil.entity.ClienteMoradorEdificio;
import br.com.system.gestaoConstrucaoCivil.entity.ClienteMoradorOutros;
import br.com.system.gestaoConstrucaoCivil.service.ClienteMoradorService;

@RestController
@RequestMapping("/rest/morador")
public class ClienteMoradorRestController {


	@Autowired
	private ClienteMoradorService clienteMoradorService;

	

	@GetMapping(value = "/lista")
	public ResponseEntity<Iterable<ClienteMorador>> buscarTodos() {
		return new ResponseEntity<Iterable<ClienteMorador>>(clienteMoradorService.lista(), HttpStatus.OK);
	}
	
	
	@GetMapping
	public ResponseEntity<Page<ClienteMorador>> lista(@RequestParam(defaultValue="0", required=false) int page
			,@RequestParam(defaultValue="0", required=false) int maxResults) {
		Page<ClienteMorador> objeto = clienteMoradorService.buscarTodos(new PageRequest(page, maxResults));
		return new ResponseEntity<Page<ClienteMorador>>(objeto, HttpStatus.OK);
	}
	

	@PostMapping(value="/edificio")
	public ResponseEntity<?> salvarEdificio(@RequestBody ClienteMoradorEdificio clienteMoradorEdificio, UriComponentsBuilder ucBuilder) {	
			clienteMoradorService.salvarOuEditar(clienteMoradorEdificio);		
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	@PostMapping(value="/casa")
	public ResponseEntity<?> salvarCasa(@RequestBody ClienteMoradorCasa clienteMorador, UriComponentsBuilder ucBuilder) {	
			clienteMoradorService.salvarOuEditar(clienteMorador);		
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping(value="/edificio")
	public ResponseEntity<?> alterarEdificio(@RequestBody ClienteMoradorEdificio clienteMoradorEdificio, UriComponentsBuilder ucBuilder) {	
			clienteMoradorService.salvarOuEditar(clienteMoradorEdificio);		
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping(value="/casa")
	public ResponseEntity<?> alterarCasa(@RequestBody ClienteMoradorCasa clienteMorador, UriComponentsBuilder ucBuilder) {	
			clienteMoradorService.salvarOuEditar(clienteMorador);		
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteMorador> buscarPorId(@PathVariable Long id) {
		return new ResponseEntity<ClienteMorador>(clienteMoradorService.buscarPorId(id), HttpStatus.OK);
	}

}
