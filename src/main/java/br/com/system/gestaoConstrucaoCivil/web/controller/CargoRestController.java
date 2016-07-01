package br.com.system.gestaoConstrucaoCivil.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.gestaoConstrucaoCivil.entity.Cargo;
import br.com.system.gestaoConstrucaoCivil.service.CargoService;

@RestController
@RequestMapping("/rest/cargo") 
public class CargoRestController {

	 @Autowired
	 private CargoService cargoService;
	 
	 @RequestMapping(method = RequestMethod.GET)
	 public ResponseEntity<Iterable<Cargo>> buscarCargos() {
	  Iterable<Cargo> cargo = cargoService.buscarTodos();
	  return new ResponseEntity<Iterable<Cargo>>(cargo, HttpStatus.OK);
	 }
	
}
