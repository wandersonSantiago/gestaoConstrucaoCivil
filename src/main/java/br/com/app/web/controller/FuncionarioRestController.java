package br.com.app.web.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.entity.Funcionario;
import br.com.app.service.FuncionarioService;
import br.com.app.service.PessoaService;

@RestController
@RequestMapping("/rest/recursosHumanos/funcionario")
public class FuncionarioRestController {

	@Autowired
	private PessoaService pessoaService;
	@Autowired
	private FuncionarioService funcionarioService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscarFuncionariosEngenheiro")
	public Collection<Funcionario> buscarEngenheiros() {
		return funcionarioService.buscarFuncionarioEngenheiro();

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista")
	public Collection<Funcionario> buscarTodos() {

		return funcionarioService.buscarTodos();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Page<Funcionario> listaComPaginacao(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "0", required = false) int maxResults) {
		return funcionarioService.listaComPaginacao(PageRequest.of(page, maxResults));

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscaPorId/{id}")
	public Optional<Funcionario> buscarPorId(@PathVariable Long id) {

		return funcionarioService.buscarFuncionarioPorId(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salva")
	public void salvar(@RequestBody Funcionario funcionario) {
		pessoaService.salvarOuEditar(funcionario);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/altera")
	public void altera(@RequestBody Funcionario funcionario) {
		pessoaService.salvarOuEditar(funcionario);

	}
}
