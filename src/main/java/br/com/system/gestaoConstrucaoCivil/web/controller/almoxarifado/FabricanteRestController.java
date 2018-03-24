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

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Fabricante;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.FabricanteService;

@RestController
@RequestMapping("/rest/almoxarifado/fabricante")
public class FabricanteRestController {

	@Autowired
	private FabricanteService fabricanteService;
	
	
	@GetMapping(value = "/lista")
	public ResponseEntity<Iterable<Fabricante>> buscarTodos() {
		return new ResponseEntity<Iterable<Fabricante>>(fabricanteService.buscarTodos(), HttpStatus.OK);
	}

	@GetMapping(value = "/buscaPorId/{id}")
	public ResponseEntity<Fabricante> buscarPorId(@PathVariable Long id) {

		return new ResponseEntity<Fabricante>(fabricanteService.buscarPorId(id), HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salva")
	public void  salvar(@RequestBody Fabricante fabricante) {
		fabricanteService.salvarOuEditar(fabricante);
		 
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/altera")
	public void alterar(@RequestBody Fabricante fabricante) {
		fabricanteService.salvarOuEditar(fabricante);
		 
	}
}
