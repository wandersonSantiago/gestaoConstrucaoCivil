package br.com.system.gestaoConstrucaoCivil.web.controller.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.servicos.ServicoCasa;
import br.com.system.gestaoConstrucaoCivil.service.servicos.ServicoCasaService;

@RestController
@RequestMapping("/rest/servico/casa/vincular")
public class ServicoCasaRestController {

	@Autowired
	private ServicoCasaService servicoCasaService;

	@PostMapping(value = "/salva")
	public ResponseEntity<ServicoCasa> salvar(@RequestBody ServicoCasa servico, UriComponentsBuilder ucBuilder) {
		servicoCasaService.salvarOuEditar(servico);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				ucBuilder.path("/rest/servico/vincular/salva/{id}").buildAndExpand(servico.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@GetMapping(value = "/lista")
	public ResponseEntity<Iterable<ServicoCasa>> buscarTodos() {

		return new ResponseEntity<Iterable<ServicoCasa>>(servicoCasaService.buscarTodos(), HttpStatus.OK);
	}

	@GetMapping(value = "/buscaPorId/{id}")
	public ResponseEntity<ServicoCasa> buscarPorId(@PathVariable Long id) {
		return new ResponseEntity<ServicoCasa>(servicoCasaService.buscarPorId(id), HttpStatus.OK);
	}
}
