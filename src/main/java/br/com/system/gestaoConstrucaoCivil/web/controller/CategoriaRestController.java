package br.com.system.gestaoConstrucaoCivil.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import br.com.system.gestaoConstrucaoCivil.service.CategoriaService;


@RestController
@RequestMapping("/rest/categoria") 
public class CategoriaRestController {

	 
	 @Autowired
	 private CategoriaService categoriaService;
	 
	
	 

}
	