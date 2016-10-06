package br.com.system.gestaoConstrucaoCivil.web.controller.almoxarifado;

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

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Fabricante;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.FabricanteService;

@RestController
@RequestMapping("/rest/almoxarifado/fabricante")
public class FabricanteRestController {

	@Autowired
	private FabricanteService fabricanteService;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/lista")
	public ResponseEntity<Iterable<Fabricante>> buscarFabricantes() {
		Iterable<Fabricante> fabricantes = fabricanteService.buscarTodos();
		return new ResponseEntity<Iterable<Fabricante>>(fabricantes, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
	public ResponseEntity<Fabricante> buscarFabricantePorId(@PathVariable Long id) {

		return new ResponseEntity<Fabricante>(fabricanteService.buscarPorId(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/salva", method = RequestMethod.POST)
	public ResponseEntity salvarFabricante(@RequestBody Fabricante fabricante, UriComponentsBuilder ucBuilder) {
		fabricanteService.salvarOuEditar(fabricante);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				ucBuilder.path("rest/almoxarifado/fabricante/salva/{id}").buildAndExpand(fabricante.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/altera", method = RequestMethod.PUT)
	public ResponseEntity alterarFabricante(@RequestBody Fabricante fabricante, UriComponentsBuilder ucBuilder) {
		fabricanteService.salvarOuEditar(fabricante);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("rest/almoxarifado/fabricante/altera/{fabricante}")
				.buildAndExpand(fabricante.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
}
