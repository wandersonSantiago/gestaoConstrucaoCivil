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

import br.com.system.gestaoConstrucaoCivil.entity.EmpresaContratante;
import br.com.system.gestaoConstrucaoCivil.service.EmpresaContratanteService;

@RestController
@RequestMapping("rest/empresaContratada")
public class EmpresaContratanteRestController {

	@Autowired
	private EmpresaContratanteService empresaContratanteService;

	@GetMapping(value = "/lista")
	public ResponseEntity<Iterable<EmpresaContratante>> buscarTodos() {

		return new ResponseEntity<Iterable<EmpresaContratante>>(empresaContratanteService.buscarTodos(), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Page<EmpresaContratante>> lista(@RequestParam(defaultValue="0", required=false) int page
			,@RequestParam(defaultValue="0", required=false) int maxResults) {
		Page<EmpresaContratante> empresa = empresaContratanteService.findAll(new PageRequest(page, maxResults));
		return new ResponseEntity<Page<EmpresaContratante>>(empresa, HttpStatus.OK);
	}
	
	@GetMapping(value = "/buscaPorId/{id}")
	public ResponseEntity<EmpresaContratante> buscarPorId(@PathVariable Long id) {

		return new ResponseEntity<EmpresaContratante>(empresaContratanteService.buscarPorId(id), HttpStatus.OK);
	}

	@PostMapping(value = "/salva")
	public ResponseEntity<EmpresaContratante> salvar(@RequestBody EmpresaContratante empresaContratante,
			UriComponentsBuilder ucBuilder) {

		empresaContratanteService.salvarOuEditar(empresaContratante);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("rest/empresaContratada/salva/{empresa}")
				.buildAndExpand(empresaContratante.getId()).toUri());

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@PutMapping(value = "/altera")
	public ResponseEntity<EmpresaContratante> alterar(@RequestBody EmpresaContratante empresaContratante,
			UriComponentsBuilder ucBuilder) {

		empresaContratanteService.salvarOuEditar(empresaContratante);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("rest/empresaContratada/altera/{empresa}")
				.buildAndExpand(empresaContratante.getId()).toUri());

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
}
