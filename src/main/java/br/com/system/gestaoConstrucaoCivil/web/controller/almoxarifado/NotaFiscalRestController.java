package br.com.system.gestaoConstrucaoCivil.web.controller.almoxarifado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.NotaFiscal;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.NotaFiscalService;

@RestController
@RequestMapping("/rest/notaFiscal")
public class NotaFiscalRestController {

	@Autowired
	 private NotaFiscalService notaFiscalService;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/lista")
	public ResponseEntity<Iterable<NotaFiscal>> buscarTodos() {
		Iterable<NotaFiscal> fornecedor = notaFiscalService.buscarTodos();
		return new ResponseEntity<Iterable<NotaFiscal>>(fornecedor, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
	public ResponseEntity<NotaFiscal> buscarPorId(@PathVariable Long id) {

		return new ResponseEntity<NotaFiscal>(notaFiscalService.buscarPorId(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/salva", method = RequestMethod.POST)
	public ResponseEntity salvar(@RequestBody NotaFiscal notaFiscal, UriComponentsBuilder ucBuilder) {
		notaFiscalService.salvarOuEditar(notaFiscal);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				ucBuilder.path("rest/notaFiscal/salva/{id}").buildAndExpand(notaFiscal.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/altera", method = RequestMethod.PUT)
	public ResponseEntity alterar(@RequestBody NotaFiscal notaFiscal, UriComponentsBuilder ucBuilder) {
		notaFiscalService.salvarOuEditar(notaFiscal);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("rest/notaFiscal/altera/{notaFiscal}")
				.buildAndExpand(notaFiscal.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
}
