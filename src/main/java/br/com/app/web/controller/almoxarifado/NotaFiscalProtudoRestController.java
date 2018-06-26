package br.com.app.web.controller.almoxarifado;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	@GetMapping(value = "/{id}")
	public  Optional<NotaFiscalProduto> findById(@PathVariable Long id) {
		return  notaFiscalProdutoService.findById(id);
	}

	@GetMapping(value = "/buscar")
	public ResponseEntity<Page<NotaFiscalProduto>> findByDescricao(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="notaFiscal.numero") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction,
			@RequestParam(value="descricao", required = false , defaultValue="")String descricao) {

		Page<NotaFiscalProduto> list = null;
		
		if(descricao.isEmpty() || descricao.equalsIgnoreCase("")) {
			list = notaFiscalProdutoService.findAll(PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}else {
			list = notaFiscalProdutoService.findByCodigo(descricao, PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}
		
		return ResponseEntity.ok().body(list);
	}

	
}
