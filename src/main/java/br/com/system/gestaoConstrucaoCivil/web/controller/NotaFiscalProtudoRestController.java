package br.com.system.gestaoConstrucaoCivil.web.controller;

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

import br.com.system.gestaoConstrucaoCivil.entity.NotaFiscalProtudo;
import br.com.system.gestaoConstrucaoCivil.service.NotaFiscalProdutoService;

@RestController
@RequestMapping("")
public class NotaFiscalProtudoRestController {

	@Autowired
	private NotaFiscalProdutoService notaFiscalProdutoService;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/lista")
	public ResponseEntity<Iterable<NotaFiscalProtudo>> buscarNotaFiscalProduto() {
		Iterable<NotaFiscalProtudo> notaFiscalProtudo = notaFiscalProdutoService.buscarTodos();
		return new ResponseEntity<Iterable<NotaFiscalProtudo>>(notaFiscalProtudo , HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarPorNumeroNota/{numero}", method = RequestMethod.GET)
	public ResponseEntity<NotaFiscalProtudo> buscarPorNumeroNota(@PathVariable Long numero) {

		return new ResponseEntity<NotaFiscalProtudo>(notaFiscalProdutoService.buscarPorId(numero), HttpStatus.OK);
	}

	@RequestMapping(value = "/salva", method = RequestMethod.POST)
	public ResponseEntity salva(@RequestBody NotaFiscalProtudo notaFiscalProtudo, UriComponentsBuilder ucBuilder) {
		notaFiscalProdutoService.salvarOuEditar(notaFiscalProtudo);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				ucBuilder.path("").buildAndExpand(notaFiscalProtudo.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/altera", method = RequestMethod.PUT)
	public ResponseEntity alterarFornecedor(@RequestBody NotaFiscalProtudo notaFiscalProtudo, UriComponentsBuilder ucBuilder) {
		notaFiscalProdutoService.salvarOuEditar(notaFiscalProtudo);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("")
				.buildAndExpand(notaFiscalProtudo.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
}
