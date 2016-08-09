package br.com.system.gestaoConstrucaoCivil.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.Fornecedor;
import br.com.system.gestaoConstrucaoCivil.service.FornecedorService;

@RestController
@RequestMapping("/rest/almoxarifado")
public class FornecedorRestController {

	@Autowired
	private FornecedorService fornecedorService;

	@RequestMapping(method = RequestMethod.GET, value = "/listarFornecedor")
	public ResponseEntity<Iterable<Fornecedor>> buscarFornecedores() {

		Iterable<Fornecedor> fornecedor = fornecedorService.buscarTodos();
		return new ResponseEntity<Iterable<Fornecedor>>(fornecedor, HttpStatus.OK);
	}

	@RequestMapping(value = "/listarFornecedorPorId/{id}", method = RequestMethod.GET)
	public ResponseEntity<Fornecedor> buscarFornecedorPorId(@PathVariable Long id) {

		return new ResponseEntity<Fornecedor>(fornecedorService.buscarFornecedorPorId(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/cadastrarFornecedor", method = RequestMethod.POST)
	public ResponseEntity salva(@RequestBody Fornecedor fornecedor, UriComponentsBuilder ucBuilder) {
		fornecedorService.salvarOuEditar(fornecedor);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				ucBuilder.path("rest/almoxarifado/cadastrarFornecedor/{id}").buildAndExpand(fornecedor.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/alterarFornecedor", method = RequestMethod.PUT)
	public ResponseEntity alterarFornecedor(@RequestBody Fornecedor fornecedor, UriComponentsBuilder ucBuilder) {
		fornecedorService.salvarOuEditar(fornecedor);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("rest/almoxarifado/alterarFornecedor/{fornecedor}")
				.buildAndExpand(fornecedor.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

}
