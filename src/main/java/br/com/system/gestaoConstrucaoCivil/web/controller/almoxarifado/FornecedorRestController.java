package br.com.system.gestaoConstrucaoCivil.web.controller.almoxarifado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Fornecedor;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.FornecedorService;

@RestController
@RequestMapping("/rest/almoxarifado/fornecedor")
public class FornecedorRestController {

	@Autowired
	private FornecedorService fornecedorService;
	
	@GetMapping(value = "/lista")
	public ResponseEntity<Iterable<Fornecedor>> buscarTodos() {
		return new ResponseEntity<Iterable<Fornecedor>>(fornecedorService.buscarTodos(), HttpStatus.OK);
	}

	@GetMapping(value = "/buscaPorId/{id}")
	public ResponseEntity<Fornecedor> buscarPorId(@PathVariable Long id) {

		return new ResponseEntity<Fornecedor>(fornecedorService.buscarPorId(id), HttpStatus.OK);
	}

	@PostMapping(value = "/salva")
	public ResponseEntity<Fornecedor> salvar(@RequestBody Fornecedor fornecedor, UriComponentsBuilder ucBuilder) {
		fornecedorService.salvarOuEditar(fornecedor);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				ucBuilder.path("rest/almoxarifado/fornecedor/salva/{id}").buildAndExpand(fornecedor.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@PutMapping(value = "/altera")
	public ResponseEntity<Fornecedor> alterar(@RequestBody Fornecedor fornecedor, UriComponentsBuilder ucBuilder) {
		fornecedorService.salvarOuEditar(fornecedor);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("rest/almoxarifado/fornecedor/altera/{fornecedor}")
				.buildAndExpand(fornecedor.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

}
