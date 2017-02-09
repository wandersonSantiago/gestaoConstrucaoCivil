package br.com.system.gestaoConstrucaoCivil.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.Cargo;
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

	@GetMapping(value = "/buscarFuncionariosEngenheiro")
	public ResponseEntity<Iterable<Funcionario>> buscarEngenheiros() {
		return new ResponseEntity<Iterable<Funcionario>>(funcionarioService.buscarFuncionarioEngenheiro(), HttpStatus.OK);
	}

	@GetMapping(value = "/lista")
	public ResponseEntity<Iterable<Funcionario>> buscarTodos() {

		return new ResponseEntity<Iterable<Funcionario>>(funcionarioService.buscarTodos(), HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<Page<Funcionario>> listaComPaginacao(@RequestParam(defaultValue="0", required=false) int page
			,@RequestParam(defaultValue="0", required=false) int maxResults) {
		Page<Funcionario> objeto = funcionarioService.listaComPaginacao(new PageRequest(page, maxResults));
		return new ResponseEntity<Page<Funcionario>>(objeto, HttpStatus.OK);
	}

	@GetMapping(value = "/buscaPorId/{id}")
	public ResponseEntity<Funcionario> buscarPorId(@PathVariable Long id) {
		
		return new ResponseEntity<Funcionario>(funcionarioService.buscarFuncionarioPorId(id), HttpStatus.OK);
	}

	@PostMapping(value = "/salva")
	public ResponseEntity<Funcionario> salvar(@RequestBody Funcionario funcionario, UriComponentsBuilder ucBuilder) {
		pessoaService.salvarOuEditar(funcionario);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/rest/recursosHumanos/funcionario/salva/{id}")
				.buildAndExpand(funcionario.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@PutMapping(value = "/altera")
	public ResponseEntity<Funcionario> altera(@RequestBody Funcionario funcionario, UriComponentsBuilder ucBuilder) {
		pessoaService.salvarOuEditar(funcionario);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/rest/recursosHumanos/funcionario/salva/{id}")
				.buildAndExpand(funcionario.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
}
