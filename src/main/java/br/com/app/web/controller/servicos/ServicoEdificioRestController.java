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

import br.com.app.entity.servicos.ServicoEdificio;
import br.com.app.service.servicos.ServicoEdificioService;

@RestController
@RequestMapping("/rest/servico/edificio/vincular")
public class ServicoEdificioRestController {

	@Autowired
	private ServicoEdificioService servicoEdificioService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salva")
	public void salvar(@RequestBody ServicoEdificio servico) {
		servicoEdificioService.salvarOuEditar(servico);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping
	public void altera(@RequestBody ServicoEdificio servico) {
		servicoEdificioService.salvarOuEditar(servico);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/vistoria")
	public void alteraVistoria(@RequestBody ServicoEdificio servico) {
		servicoEdificioService.salvarOuEditarVistoria(servico);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista")
	public Collection<ServicoEdificio> buscarTodos() {
		return servicoEdificioService.lista();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/vistoria/torre/{torre}/andar/{andar}/apartamento/{apartamento}")
	public Collection<ServicoEdificio> buscarServicosPorApartamento(@PathVariable Integer torre,
			@PathVariable Integer andar, @PathVariable Integer apartamento) {
		return servicoEdificioService.buscarServicosPorApartamento(torre, andar, apartamento);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Page<ServicoEdificio> lista(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "0", required = false) int maxResults) {
		return servicoEdificioService.buscarTodos(new PageRequest(page, maxResults));
		
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscaPorId/{id}")
	public Optional<ServicoEdificio> buscarPorId(@PathVariable Long id) {
		return servicoEdificioService.buscarPorId(id);
	}

}
