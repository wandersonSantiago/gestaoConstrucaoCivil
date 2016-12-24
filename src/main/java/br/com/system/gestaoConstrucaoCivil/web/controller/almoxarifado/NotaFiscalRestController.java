package br.com.system.gestaoConstrucaoCivil.web.controller.almoxarifado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.NotaFiscal;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.NotaFiscalService;

@RestController
@RequestMapping("/rest/notaFiscal")
public class NotaFiscalRestController {

	@Autowired
	 private NotaFiscalService notaFiscalService;
	
	
	@GetMapping(value = "/lista")
	public ResponseEntity<Iterable<NotaFiscal>> buscarTodos() {
		return new ResponseEntity<Iterable<NotaFiscal>>(notaFiscalService.buscarTodos(), HttpStatus.OK);
	}

	@GetMapping(value = "/buscaPorId/{id}")
	public ResponseEntity<NotaFiscal> buscarPorId(@PathVariable Long id) {

		return new ResponseEntity<NotaFiscal>(notaFiscalService.buscarPorId(id), HttpStatus.OK);
	}

	@PostMapping(value = "/salva")
	public ResponseEntity<NotaFiscal> salvar(@RequestBody NotaFiscal notaFiscal, UriComponentsBuilder ucBuilder) {
		notaFiscalService.salvarOuEditar(notaFiscal);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				ucBuilder.path("rest/notaFiscal/salva/{id}").buildAndExpand(notaFiscal.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@PutMapping(value = "/altera")
	public ResponseEntity alterar(@RequestBody NotaFiscal notaFiscal, UriComponentsBuilder ucBuilder) {
		notaFiscalService.salvarOuEditar(notaFiscal);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("rest/notaFiscal/altera/{notaFiscal}")
				.buildAndExpand(notaFiscal.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
}
