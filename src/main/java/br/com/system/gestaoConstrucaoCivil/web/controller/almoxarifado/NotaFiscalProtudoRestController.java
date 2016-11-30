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

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.NotaFiscalProduto;
import br.com.system.gestaoConstrucaoCivil.enuns.TipoNotaEnum;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.NotaFiscalProdutoService;

@RestController
@RequestMapping("/rest/notaFiscalProduto")
public class NotaFiscalProtudoRestController {

	@Autowired
	private NotaFiscalProdutoService notaFiscalProdutoService;

	@RequestMapping(method = RequestMethod.GET, value = "/lista")
	public ResponseEntity<Iterable<NotaFiscalProduto>> buscarTodos() {
		Iterable<NotaFiscalProduto> notaFiscalProtudo = notaFiscalProdutoService.buscarTodos();
		return new ResponseEntity<Iterable<NotaFiscalProduto>>(notaFiscalProtudo, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarPorNumeroNota/{numero}", method = RequestMethod.GET)
	public ResponseEntity<NotaFiscalProduto> buscarPorNumeroNota(@PathVariable Long numero) {

		return new ResponseEntity<NotaFiscalProduto>(notaFiscalProdutoService.buscarPorId(numero), HttpStatus.OK);
	}

	@RequestMapping(value = "/salva", method = RequestMethod.POST)
	public ResponseEntity salvar(@RequestBody NotaFiscalProduto notaFiscalProtudo, UriComponentsBuilder ucBuilder) {
		
		notaFiscalProtudo.getNotaFiscal().setTipoNota(TipoNotaEnum.NOTA_FISCAL_ENTRADA);
		notaFiscalProdutoService.salvarOuEditar(notaFiscalProtudo);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("").buildAndExpand(notaFiscalProtudo.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/altera", method = RequestMethod.PUT)
	public ResponseEntity alterar(@RequestBody NotaFiscalProduto notaFiscalProtudo,
			UriComponentsBuilder ucBuilder) {
		notaFiscalProdutoService.salvarOuEditar(notaFiscalProtudo);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("").buildAndExpand(notaFiscalProtudo.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
}
