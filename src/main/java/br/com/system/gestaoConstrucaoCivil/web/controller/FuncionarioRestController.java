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
import br.com.system.gestaoConstrucaoCivil.entity.Funcionario;
import br.com.system.gestaoConstrucaoCivil.service.EnderecoService;
import br.com.system.gestaoConstrucaoCivil.service.FuncionarioService;
import br.com.system.gestaoConstrucaoCivil.service.PessoaService;


@RestController
@RequestMapping("/rest/recursosHumanos/cadastrarFuncionario")
public class FuncionarioRestController {


	 @Autowired
	 private PessoaService pessoaService;
	 @Autowired
	 private FuncionarioService funcionarioService;
	 
	 @Autowired
	 private EnderecoService enderecoService;
     
	
	 @RequestMapping(method = RequestMethod.GET, value="/listarEngenheiro")
	 public ResponseEntity<Iterable<Funcionario>> buscarEngenheiros() {	  
	  System.out.println("lista ok");
	  Iterable<Funcionario> funcionarioEngenheiro = funcionarioService.buscarFuncionarioEngenheiro();
	  return new ResponseEntity<Iterable<Funcionario>>(funcionarioEngenheiro, HttpStatus.OK);
	 }
	 @RequestMapping(method = RequestMethod.GET, value="/listarFuncionario")
	 public ResponseEntity<Iterable<Funcionario>> buscarTodos() {	  
	  
	  Iterable<Funcionario> funcionarios = funcionarioService.buscarTodos();
	  return new ResponseEntity<Iterable<Funcionario>>(funcionarios, HttpStatus.OK);
	 }
	 
	 
	 @RequestMapping(method = RequestMethod.POST)
	 public ResponseEntity salvar(@RequestBody Funcionario funcionario,UriComponentsBuilder ucBuilder)
	 {
		 enderecoService.salvarOuEditar(funcionario.getEndereco());
		 pessoaService.salvarOuEditar(funcionario);
		 HttpHeaders headers =new HttpHeaders();
		 headers.setLocation(ucBuilder.path("/rest/recursosHumanos/cadastrarFuncionario/{id}").buildAndExpand(funcionario.getId()).toUri());
		 return new ResponseEntity(headers, HttpStatus.CREATED);
	 }
}
