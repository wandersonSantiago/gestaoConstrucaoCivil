package br.com.system.gestaoConstrucaoCivil.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.gestaoConstrucaoCivil.service.FornecedorService;

@RestController
@RequestMapping("/rest/fornecedor")
public class FornecedorRestController {


	 @Autowired
	 private FornecedorService fornecedorService;
}
