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

import br.com.system.gestaoConstrucaoCivil.entity.servicos.PacoteServico;
import br.com.system.gestaoConstrucaoCivil.service.servicos.PacoteServicoService;

@RestController
@RequestMapping("/rest/servicos/pacotes")
public class PacoteServicoRestController {

	@Autowired
	private PacoteServicoService pacoteServicoService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista")
	public Collection<PacoteServico> buscarTodos() {
		return pacoteServicoService.lista();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Page<PacoteServico> lista(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "0", required = false) int maxResults) {
		return pacoteServicoService.buscarTodos(new PageRequest(page, maxResults));

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salva")
	public void salvar(@RequestBody PacoteServico pacoteServico) {
		pacoteServicoService.salvarOuEditar(pacoteServico);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/altera")
	public void alterar(@RequestBody PacoteServico pacoteServico) {
		pacoteServicoService.salvarOuEditar(pacoteServico);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscaPorId/{id}")
	public Optional<PacoteServico> buscarPorId(@PathVariable Long id) {
		return pacoteServicoService.buscarPorId(id);
	}

}
