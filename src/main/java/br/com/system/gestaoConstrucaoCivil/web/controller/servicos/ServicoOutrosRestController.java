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

import br.com.system.gestaoConstrucaoCivil.entity.servicos.ServicoOutros;
import br.com.system.gestaoConstrucaoCivil.service.servicos.ServicoOutrosService;

@RestController
@RequestMapping("/rest/servico/outros/vincular")
public class ServicoOutrosRestController {

	@Autowired
	private ServicoOutrosService servicoOutrosService;

	@PostMapping(value = "/salva")
	public ResponseEntity<ServicoOutros> salvar(@RequestBody ServicoOutros servico, UriComponentsBuilder ucBuilder) {
		servicoOutrosService.salvarOuEditar(servico);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				ucBuilder.path("/rest/servico/vincular/salva/{id}").buildAndExpand(servico.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<ServicoOutros> altera(@RequestBody ServicoOutros servico, UriComponentsBuilder ucBuilder) {
		servicoOutrosService.salvarOuEditar(servico);
		HttpHeaders headers = new HttpHeaders();		
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}	
		
	@GetMapping(value = "/lista")
	public ResponseEntity<Iterable<ServicoOutros>> buscarTodos() {
		return new ResponseEntity<Iterable<ServicoOutros>>(servicoOutrosService.lista(), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Page<ServicoOutros>> lista(@RequestParam(defaultValue="0", required=false) int page
			,@RequestParam(defaultValue="0", required=false) int maxResults) {
		Page<ServicoOutros> objeto = servicoOutrosService.buscarTodos(new PageRequest(page, maxResults));
		return new ResponseEntity<Page<ServicoOutros>>(objeto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/buscaPorId/{id}")
	public ResponseEntity<ServicoOutros> buscarPorId(@PathVariable Long id) {
		return new ResponseEntity<ServicoOutros>(servicoOutrosService.buscarPorId(id), HttpStatus.OK);
	}
	
}
