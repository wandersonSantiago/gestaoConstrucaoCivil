package br.com.system.gestaoConstrucaoCivil.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.Cargo;
import br.com.system.gestaoConstrucaoCivil.service.CargoService;
import br.com.system.gestaoConstrucaoCivil.service.interfaceservice.ICargoService;



@RestController
@RequestMapping("/rest/recursosHumanos/cargo")
public class CargoRestController implements ICargo {

	@Autowired
	private ICargoService<Cargo> cargoService;
	
	@Autowired
	private CargoService cargoServices;
	
	@PreAuthorize("hasAnyRole('ROLE_MODULO_ADMIN','ROLE_MODULO_RECURSOS_HUMANOS_CARGO_CONSULTAR')")
	public ResponseEntity<Iterable<Cargo>> buscarCargos() {
		return new ResponseEntity<Iterable<Cargo>>(cargoService.buscarTodos(), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('ROLE_MODULO_ADMIN','ROLE_MODULO_RECURSOS_HUMANOS_CARGO_CONSULTAR')")
	@GetMapping(value="/lista/paginacao/")
	public ResponseEntity<Page<Cargo>> listaComPaginacao(@RequestParam(defaultValue="0", required=false) int page
			,@RequestParam(defaultValue="0", required=false) int maxResults) {
		Page<Cargo> objeto = cargoServices.listaComPaginacao(new PageRequest(page, maxResults));
		return new ResponseEntity<Page<Cargo>>(objeto, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_MODULO_ADMIN','ROLE_MODULO_RECURSOS_HUMANOS_CARGO_CONSULTAR')")
	public ResponseEntity<Cargo> buscarCargoPorId(@PathVariable Long id) {
		return new ResponseEntity<Cargo>(cargoService.buscarPorId(id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_MODULO_ADMIN','ROLE_MODULO_RECURSOS_HUMANOS_CARGO_CADASTRAR')")
	public ResponseEntity<Cargo> salva(@RequestBody Cargo cargo, UriComponentsBuilder ucBuilder) {
		
		//cargo.setDescricao(null);
		cargoService.salvarOuEditar(cargo);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/rest/recursosHumanos/salva/{id}").buildAndExpand(cargo.getId()).toUri());
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_MODULO_ADMIN','ROLE_MODULO_RECURSOS_HUMANOS_CARGO_ALTERAR')")
	public ResponseEntity<Cargo> updateCargo(@RequestBody Cargo cargo, UriComponentsBuilder ucBuilder) {
		cargoService.salvarOuEditar(cargo);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/rest/recursosHumanos/altera/{id}").buildAndExpand(cargo.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

}
