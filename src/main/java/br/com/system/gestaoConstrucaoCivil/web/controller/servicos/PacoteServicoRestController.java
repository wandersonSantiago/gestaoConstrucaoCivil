package br.com.system.gestaoConstrucaoCivil.web.controller.servicos;

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

import br.com.system.gestaoConstrucaoCivil.entity.servicos.PacoteServico;
import br.com.system.gestaoConstrucaoCivil.service.servicos.PacoteServicoService;

@RestController
@RequestMapping("/rest/servicos/pacotes")
public class PacoteServicoRestController {

	@Autowired
	private PacoteServicoService pacoteServicoService;

	

	@GetMapping(value = "/lista")
	public ResponseEntity<Iterable<PacoteServico>> buscarTodos() {
		return new ResponseEntity<Iterable<PacoteServico>>(pacoteServicoService.lista(), HttpStatus.OK);
	}
	
	
	@GetMapping
	public ResponseEntity<Page<PacoteServico>> lista(@RequestParam(defaultValue="0", required=false) int page
			,@RequestParam(defaultValue="0", required=false) int maxResults) {
		Page<PacoteServico> objeto = pacoteServicoService.buscarTodos(new PageRequest(page, maxResults));
		return new ResponseEntity<Page<PacoteServico>>(objeto, HttpStatus.OK);
	}
	

	@PostMapping(value = "/salva")
	public ResponseEntity<PacoteServico> salvar(@RequestBody PacoteServico pacoteServico, UriComponentsBuilder ucBuilder) {
		pacoteServicoService.salvarOuEditar(pacoteServico);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				ucBuilder.path("/rest/servicos/pacotes/salva/{id}").buildAndExpand(pacoteServico.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@PutMapping(value = "/altera")
	public ResponseEntity<PacoteServico> alterar(@RequestBody PacoteServico pacoteServico, UriComponentsBuilder ucBuilder) {
		pacoteServicoService.salvarOuEditar(pacoteServico);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				ucBuilder.path("/rest/servicos/pacotes/altera/{id}").buildAndExpand(pacoteServico.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@GetMapping(value = "/buscaPorId/{id}")
	public ResponseEntity<PacoteServico> buscarPorId(@PathVariable Long id) {
		return new ResponseEntity<PacoteServico>(pacoteServicoService.buscarPorId(id), HttpStatus.OK);
	}

}
