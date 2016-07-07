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
	 private EnderecoService enderecoService;
     
	 @RequestMapping(method = RequestMethod.POST)
	 public ResponseEntity salvar(@RequestBody Funcionario funcionario,UriComponentsBuilder ucBuilder)
	 {
		 System.out.println("CHAMOU SALVAR FUNCIONARIO");
		
		 System.out.println("Nome:" + funcionario.getNomeCompleto());
		 System.out.println("data:" + funcionario.getDataNascimento());
		 System.out.println("Carteira:" + funcionario.getCarteiraTrabalho());
		 System.out.println("Rua" + funcionario.getEndereco().getRua());
		 System.out.println("cargo" + funcionario.getCargo());
		 enderecoService.salvarOuEditar(funcionario.getEndereco());
		 pessoaService.salvarOuEditar(funcionario);
		 HttpHeaders headers =new HttpHeaders();
		 headers.setLocation(ucBuilder.path("/rest/recursosHumanos/cadastrarFuncionario/{id}").buildAndExpand(funcionario.getId()).toUri());
		 return new ResponseEntity(headers, HttpStatus.CREATED);
	 }
}
