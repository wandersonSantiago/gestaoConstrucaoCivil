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

import br.com.system.gestaoConstrucaoCivil.entity.Funcionario;
import br.com.system.gestaoConstrucaoCivil.service.FuncionarioService;
import br.com.system.gestaoConstrucaoCivil.service.PessoaService;

@RestController
@RequestMapping("/rest/recursosHumanos/funcionario")
public class FuncionarioRestController {

	@Autowired
	private PessoaService pessoaService;
	@Autowired
	private FuncionarioService funcionarioService;

	@RequestMapping(method = RequestMethod.GET, value = "/buscarFuncionariosEngenheiro")
	public ResponseEntity<Iterable<Funcionario>> buscarEngenheiros() {
		Iterable<Funcionario> funcionarioEngenheiro = funcionarioService.buscarFuncionarioEngenheiro();
		return new ResponseEntity<Iterable<Funcionario>>(funcionarioEngenheiro, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/lista")
	public ResponseEntity<Iterable<Funcionario>> buscarTodos() {

		Iterable<Funcionario> funcionarios = funcionarioService.buscarTodos();
		return new ResponseEntity<Iterable<Funcionario>>(funcionarios, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
	public ResponseEntity<Funcionario> buscarPorId(@PathVariable Long id) {
		
		return new ResponseEntity<Funcionario>(funcionarioService.buscarFuncionarioPorId(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/salva", method = RequestMethod.POST)
	public ResponseEntity salvar(@RequestBody Funcionario funcionario, UriComponentsBuilder ucBuilder) {
		pessoaService.salvarOuEditar(funcionario);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/rest/recursosHumanos/funcionario/salva/{id}")
				.buildAndExpand(funcionario.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/altera", method = RequestMethod.PUT)
	public ResponseEntity altera(@RequestBody Funcionario funcionario, UriComponentsBuilder ucBuilder) {
		pessoaService.salvarOuEditar(funcionario);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/rest/recursosHumanos/funcionario/salva/{id}")
				.buildAndExpand(funcionario.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
}
