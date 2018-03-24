package br.com.system.gestaoConstrucaoCivil.web.controller.almoxarifado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salva")
	public void salvar(@RequestBody Fornecedor fornecedor) {
		fornecedorService.salvarOuEditar(fornecedor);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/altera")
	public void alterar(@RequestBody Fornecedor fornecedor) {
		fornecedorService.salvarOuEditar(fornecedor);
		 
	}

}
