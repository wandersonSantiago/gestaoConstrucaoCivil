package br.com.system.gestaoConstrucaoCivil.web.controller;

import java.util.Collection;

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

import br.com.system.gestaoConstrucaoCivil.entity.EmpresaContratante;
import br.com.system.gestaoConstrucaoCivil.service.EmpresaContratanteService;

@RestController
@RequestMapping("rest/empresaContratada")
public class EmpresaContratanteRestController {

	@Autowired
	private EmpresaContratanteService empresaContratanteService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista")
	public Collection<EmpresaContratante> buscarTodos() {

		return empresaContratanteService.buscarTodos();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Page<EmpresaContratante> lista(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "0", required = false) int maxResults) {
		return empresaContratanteService.buscarTodos(new PageRequest(page, maxResults));

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscaPorId/{id}")
	public EmpresaContratante buscarPorId(@PathVariable Long id) {

		return empresaContratanteService.buscarPorId(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salva")
	public void salvar(@RequestBody EmpresaContratante empresaContratante) {

		empresaContratanteService.salvarOuEditar(empresaContratante);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/altera")
	public void alterar(@RequestBody EmpresaContratante empresaContratante) {

		empresaContratanteService.salvarOuEditar(empresaContratante);

	}
}
