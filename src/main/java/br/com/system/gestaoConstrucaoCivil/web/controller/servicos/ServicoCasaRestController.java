package br.com.system.gestaoConstrucaoCivil.web.controller.servicos;

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

import br.com.system.gestaoConstrucaoCivil.entity.servicos.ServicoCasa;
import br.com.system.gestaoConstrucaoCivil.service.servicos.ServicoCasaService;

@RestController
@RequestMapping("/rest/servico/casa/vincular")
public class ServicoCasaRestController {

	@Autowired
	private ServicoCasaService servicoCasaService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salva")
	public void salvar(@RequestBody ServicoCasa servico) {
		servicoCasaService.salvarOuEditar(servico);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping
	public void altera(@RequestBody ServicoCasa servico) {
		servicoCasaService.salvarOuEditar(servico);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista")
	public Collection<ServicoCasa> buscarTodos() {
		return servicoCasaService.lista();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Page<ServicoCasa> lista(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "0", required = false) int maxResults) {
		return servicoCasaService.buscarTodos(new PageRequest(page, maxResults));

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscaPorId/{id}")
	public Optional<ServicoCasa> buscarPorId(@PathVariable Long id) {
		return servicoCasaService.buscarPorId(id);
	}
}
