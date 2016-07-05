package br.com.system.gestaoConstrucaoCivil.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.Cargo;
import br.com.system.gestaoConstrucaoCivil.service.CargoService;

@RestController
@RequestMapping("/rest/cargo/cadastrarCargo") 
public class CargoRestController {

	 @Autowired
	 private CargoService cargoService;
	 
	 @RequestMapping(method = RequestMethod.GET)
	 public ResponseEntity<Iterable<Cargo>> buscarCargos() {
	  
	  System.out.println("CHAMOU LISTA");
	  Iterable<Cargo> cargo = cargoService.buscarTodos();
	  return new ResponseEntity<Iterable<Cargo>>(cargo, HttpStatus.OK);
	 }
	 @RequestMapping(method = RequestMethod.POST)
	 public ResponseEntity salva(@RequestBody Cargo cargo,UriComponentsBuilder ucBuilder)
	 {
		 cargoService.salvarOuEditar(cargo);
		 HttpHeaders headers =new HttpHeaders();
		 headers.setLocation(ucBuilder.path("/rest/cargo/cadastrarCargo").buildAndExpand(cargo.getId()).toUri());
		 return new ResponseEntity(headers, HttpStatus.CREATED);
	 }
	
}
