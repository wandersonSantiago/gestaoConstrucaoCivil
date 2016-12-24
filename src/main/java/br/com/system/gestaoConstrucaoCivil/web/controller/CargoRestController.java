package br.com.system.gestaoConstrucaoCivil.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.Cargo;
import br.com.system.gestaoConstrucaoCivil.service.interfaceservice.ICargoService;



@RestController
@RequestMapping("/rest/recursosHumanos/cargo")
public class CargoRestController implements ICargo {

	@Autowired
	private ICargoService<Cargo> cargoService;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CARGO_CONSULTA')")
	public ResponseEntity<Iterable<Cargo>> buscarCargos() {
		return new ResponseEntity<Iterable<Cargo>>(cargoService.buscarTodos(), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CARGO_CONSULTA')")
	public ResponseEntity<Cargo> buscarCargoPorId(@PathVariable Long id) {
		return new ResponseEntity<Cargo>(cargoService.buscarPorId(id), HttpStatus.OK);
	}
	
	public ResponseEntity<Cargo> salva(@RequestBody Cargo cargo, UriComponentsBuilder ucBuilder) {
		cargoService.salvarOuEditar(cargo);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/rest/recursosHumanos/salva/{id}").buildAndExpand(cargo.getId()).toUri());
		return new ResponseEntity(HttpStatus.CREATED);
	}

	public ResponseEntity<Cargo> updateCargo(@RequestBody Cargo cargo, UriComponentsBuilder ucBuilder) {
		cargoService.salvarOuEditar(cargo);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/rest/recursosHumanos/altera/{id}").buildAndExpand(cargo.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

}
