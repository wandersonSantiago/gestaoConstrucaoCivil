package br.com.app.servico.api.v1.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.servico.domain.model.PacoteServico;
import br.com.app.servico.domain.service.PacoteServicoService;

@RestController
@RequestMapping("/rest/servicos/pacotes")
public class PacoteServicoResource {

	@Autowired
	private PacoteServicoService pacoteServicoService;

	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void salvar(@RequestBody PacoteServico pacoteServico) {
		pacoteServicoService.salvarOuEditar(pacoteServico);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping
	public void alterar(@RequestBody PacoteServico pacoteServico) {
		pacoteServicoService.salvarOuEditar(pacoteServico);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}")
	public Optional<PacoteServico> buscarPorId(@PathVariable Long id) {
		return pacoteServicoService.buscarPorId(id);
	}

	@GetMapping(value = "/buscar")
	public ResponseEntity<Page<PacoteServico>> findByDescricao(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="codigo") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction,
			@RequestParam(value="descricao", required = false , defaultValue="")String descricao) {

		Page<PacoteServico> list = null;
		
		if(descricao.isEmpty() || descricao.equalsIgnoreCase("")) {
			list = pacoteServicoService.findAll(PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}else {
			list = pacoteServicoService.findByDescricaoIgnoreCase(descricao, PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}
		
		return ResponseEntity.ok().body(list);
	}
}
