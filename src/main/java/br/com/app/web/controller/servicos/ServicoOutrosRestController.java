package br.com.app.web.controller.servicos;

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

import br.com.app.entity.servicos.ServicoOutros;
import br.com.app.service.servicos.ServicoOutrosService;

@RestController
@RequestMapping("/rest/servico/outros/vincular")
public class ServicoOutrosRestController {

	@Autowired
	private ServicoOutrosService servicoOutrosService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salva")
	public void salvar(@RequestBody ServicoOutros servico) {
		servicoOutrosService.salvarOuEditar(servico);
 	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping
	public void altera(@RequestBody ServicoOutros servico) {
		servicoOutrosService.salvarOuEditar(servico);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista")
	public Collection<ServicoOutros> buscarTodos() {
		return  servicoOutrosService.lista();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/vistoria/outros/{outros}")
	public Collection<ServicoOutros> buscarServicoComunitario(@PathVariable String outros) {
		return servicoOutrosService.buscarServicoComunitario(outros);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Page<ServicoOutros> lista(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "0", required = false) int maxResults) {
		return servicoOutrosService.buscarTodos(new PageRequest(page, maxResults));

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscaPorId/{id}")
	public Optional<ServicoOutros> buscarPorId(@PathVariable Long id) {
		return servicoOutrosService.buscarPorId(id);
	}

}
