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

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.NotaFiscalProduto;
import br.com.system.gestaoConstrucaoCivil.enuns.TipoNotaEnum;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.NotaFiscalProdutoService;

@RestController
@RequestMapping("/rest/notaFiscalProduto")
public class NotaFiscalProtudoRestController {

	@Autowired
	private NotaFiscalProdutoService notaFiscalProdutoService;

	@GetMapping(value = "/lista")
	public ResponseEntity<Iterable<NotaFiscalProduto>> buscarTodos() {
		return new ResponseEntity<Iterable<NotaFiscalProduto>>(notaFiscalProdutoService.buscarTodos(), HttpStatus.OK);
	}

	@GetMapping(value = "/buscarPorNumeroNota/{numero}")
	public ResponseEntity<NotaFiscalProduto> buscarPorNumeroNota(@PathVariable Long numero) {

		return new ResponseEntity<NotaFiscalProduto>(notaFiscalProdutoService.buscarPorId(numero), HttpStatus.OK);
	}

	@PostMapping(value = "/salva")
	public ResponseEntity<NotaFiscalProduto> salvar(@RequestBody NotaFiscalProduto notaFiscalProtudo, UriComponentsBuilder ucBuilder) {
		
		notaFiscalProtudo.getNotaFiscal().setTipoNota(TipoNotaEnum.NOTA_FISCAL_ENTRADA);
		notaFiscalProdutoService.salvarOuEditar(notaFiscalProtudo);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("").buildAndExpand(notaFiscalProtudo.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@PutMapping(value = "/altera")
	public ResponseEntity<NotaFiscalProduto> alterar(@RequestBody NotaFiscalProduto notaFiscalProtudo,
			UriComponentsBuilder ucBuilder) {
		notaFiscalProdutoService.salvarOuEditar(notaFiscalProtudo);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("").buildAndExpand(notaFiscalProtudo.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
}
