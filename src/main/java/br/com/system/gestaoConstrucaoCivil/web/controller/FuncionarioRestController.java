package br.com.system.gestaoConstrucaoCivil.web.controller;


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

import br.com.system.gestaoConstrucaoCivil.entity.Cargo;
import br.com.system.gestaoConstrucaoCivil.entity.EmpresaContratante;
import br.com.system.gestaoConstrucaoCivil.entity.Funcionario;
import br.com.system.gestaoConstrucaoCivil.service.EnderecoService;
import br.com.system.gestaoConstrucaoCivil.service.FuncionarioService;
import br.com.system.gestaoConstrucaoCivil.service.PessoaService;


@RestController
@RequestMapping("/rest/recursosHumanos")
public class FuncionarioRestController {


	 @Autowired
	 private PessoaService pessoaService;
	 @Autowired
	 private FuncionarioService funcionarioService;
	 
	 @Autowired
	 private EnderecoService enderecoService;
     
	
	 @RequestMapping(method = RequestMethod.GET, value="/buscaFuncionarioEngenheiro")
	 public ResponseEntity<Iterable<Funcionario>> buscarEngenheiros() {	  
	  System.out.println("lista ok");
	  Iterable<Funcionario> funcionarioEngenheiro = funcionarioService.buscarFuncionarioEngenheiro();
	  return new ResponseEntity<Iterable<Funcionario>>(funcionarioEngenheiro, HttpStatus.OK);
	 }
	 
	 @RequestMapping(method = RequestMethod.GET, value="/listarFuncionarios")
	 public ResponseEntity<Iterable<Funcionario>> buscarTodos() {	  
	  
	  Iterable<Funcionario> funcionarios = funcionarioService.buscarTodos();
	  return new ResponseEntity<Iterable<Funcionario>>(funcionarios, HttpStatus.OK);
	 }
	 
	 
		@RequestMapping(value = "/listarFuncionarioPorId/{id}", method = RequestMethod.GET)
		public ResponseEntity<Funcionario> buscarFuncionarioPorId(@PathVariable Long id) {
			System.out.println("Chamadou o lista Funcionario");
			return new ResponseEntity<Funcionario>(funcionarioService.buscarFuncionarioPorId(id), HttpStatus.OK);
		}
		
		
	 
	 @RequestMapping( value="/cadastrarFuncionario", method = RequestMethod.POST)
	 public ResponseEntity salvar(@RequestBody Funcionario funcionario,UriComponentsBuilder ucBuilder)
	 {
		 pessoaService.salvarOuEditar(funcionario);
		 HttpHeaders headers =new HttpHeaders();
		 headers.setLocation(ucBuilder.path("/rest/recursosHumanos/cadastrarFuncionario/{id}").buildAndExpand(funcionario.getId()).toUri());
		 return new ResponseEntity(headers, HttpStatus.CREATED);
	 }
}
