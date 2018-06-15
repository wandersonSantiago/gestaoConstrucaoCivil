package br.com.app.web.controller.almoxarifado;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.entity.almoxarifado.NotaFiscalProduto;
import br.com.app.enuns.TipoNotaEnum;
import br.com.app.service.almoxarifado.NotaFiscalProdutoService;

@RestController
@RequestMapping("/rest/nota-fiscal-produto")
public class NotaFiscalProtudoRestController {

	@Autowired
	private NotaFiscalProdutoService notaFiscalProdutoService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void insert(@RequestBody NotaFiscalProduto notaFiscalProtudo) {		
		notaFiscalProtudo.getNotaFiscal().setTipoNota(TipoNotaEnum.NOTA_FISCAL_ENTRADA);
		notaFiscalProdutoService.salvarOuEditar(notaFiscalProtudo);
		
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping
	public void alterar(@RequestBody NotaFiscalProduto notaFiscalProtudo) {
		notaFiscalProdutoService.salvarOuEditar(notaFiscalProtudo);
		
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Collection<NotaFiscalProduto> buscarTodos() {
		return notaFiscalProdutoService.buscarTodos();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscarPorNumeroNota/{numero}")
	public  Optional<NotaFiscalProduto> buscarPorNumeroNota(@PathVariable Long numero) {
		return  notaFiscalProdutoService.buscarPorId(numero);
	}

	
}
