package br.com.system.gestaoConstrucaoCivil.web.controller.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.servicos.PrestadoraServico;
import br.com.system.gestaoConstrucaoCivil.service.servicos.PrestadoraServicoService;

@RestController
@RequestMapping("/rest/servicos/prestadoraServico")
public class PrestadoraRestServicoController {

	@Autowired
	private PrestadoraServicoService prestadoraServicoService;

	@PreAuthorize("hasAnyRole('ROLE_MODULO_ADMIN','ROLE_PRESTADORA_SERVICOS_CONSULTAR')")
	@GetMapping(value = "/lista")
	public ResponseEntity<Iterable<PrestadoraServico>> buscarTodos() {
		return new ResponseEntity<Iterable<PrestadoraServico>>(prestadoraServicoService.lista(), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_MODULO_ADMIN','ROLE_PRESTADORA_SERVICOS_CONSULTAR')")
	@GetMapping
	public ResponseEntity<Page<PrestadoraServico>> lista(@RequestParam(defaultValue="0", required=false) int page
			,@RequestParam(defaultValue="0", required=false) int maxResults) {
		Page<PrestadoraServico> objeto = prestadoraServicoService.buscarTodos(new PageRequest(page, maxResults));
		return new ResponseEntity<Page<PrestadoraServico>>(objeto, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_MODULO_ADMIN','ROLE_PRESTADORA_SERVICOS_CONSULTAR')")
	@GetMapping(value = "/buscaPorId/{id}")
	public ResponseEntity<PrestadoraServico> buscarPorId(@PathVariable Long id) {
		return new ResponseEntity<PrestadoraServico>(prestadoraServicoService.buscarPorId(id), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_MODULO_ADMIN','ROLE_PRESTADORA_SERVICOS_CADASTRAR')")
	@PostMapping(value = "/salva")
	public ResponseEntity<PrestadoraServico> salvar(@RequestBody PrestadoraServico prestadoraServico, UriComponentsBuilder ucBuilder) {
		System.out.println(prestadoraServico);

		prestadoraServicoService.salvarOuEditar(prestadoraServico);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/rest/prestadoraServico/salva/{prestadoraServico}")
				.buildAndExpand(prestadoraServico.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}


	@PreAuthorize("hasAnyRole('ROLE_MODULO_ADMIN','ROLE_PRESTADORA_SERVICOS_ALTERAR')")
	@PutMapping(value = "/altera")
	public ResponseEntity<PrestadoraServico> alterar(@RequestBody PrestadoraServico prestadoraServico, UriComponentsBuilder ucBuilder) {

		prestadoraServicoService.salvarOuEditar(prestadoraServico);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/rest/prestadoraServico/altera/{prestadoraServico}")
				.buildAndExpand(prestadoraServico.getId()).toUri());

		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
}
