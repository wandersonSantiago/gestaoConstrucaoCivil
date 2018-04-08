package br.com.system.gestaoConstrucaoCivil.web.controller.almoxarifado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Produto;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoOutros;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.ProdutoService;

@RestController
@RequestMapping("/rest/almoxarifado/produto")
public class ProdutoRestController {

	 @Autowired
	 private ProdutoService produtoService;
	
	
	 @GetMapping(value="/lista")
	 public ResponseEntity<Iterable<Produto>> buscarTodos() {	  
	  
	  return new ResponseEntity<Iterable<Produto>>(produtoService.buscarTodos(), HttpStatus.OK);
	 }
	 
	 @GetMapping
	 public ResponseEntity<Page<Produto>> lista(@RequestParam(defaultValue="0", required=false) int page
				,@RequestParam(defaultValue="0", required=false) int maxResults) {
			Page<Produto> objeto = produtoService.buscarTodosComPaginacao(new PageRequest(page, maxResults));
			return new ResponseEntity<Page<Produto>>(objeto, HttpStatus.OK);
		}
	
	@GetMapping(value = "/buscaPorId/{id}")
	public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {

			return new ResponseEntity<Produto>(produtoService.buscaPorId(id), HttpStatus.OK);
		}

	@GetMapping(value = "/buscaPorCodigo/{codigo}")
	public ResponseEntity<Produto> buscarPorCodigo(@PathVariable String codigo)
	{
		return new ResponseEntity<Produto>(produtoService.buscarPorCodigoOuCodigoBarra(codigo),HttpStatus.OK);
	}
	
	@PostMapping(value="/salva")
	 public ResponseEntity<Produto> salvar(@RequestBody Produto produto,UriComponentsBuilder ucBuilder)
	 {
		 produtoService.salvarOuEditar(produto);
		 HttpHeaders headers = new HttpHeaders();
		 headers.setLocation(ucBuilder.path("/rest/almoxarifado/produto/salva/{id}").buildAndExpand(produto.getId()).toUri());
		 return new ResponseEntity(headers, HttpStatus.CREATED);
	 }
	 
	 @PutMapping(value="/altera")
	 public ResponseEntity<Produto> alterar(@RequestBody Produto produto,UriComponentsBuilder ucBuilder)
	 { produtoService.salvarOuEditar(produto);
		 HttpHeaders headers = new HttpHeaders();
		 headers.setLocation(ucBuilder.path("/rest/almoxarifado/produto/altera/{id}").buildAndExpand(produto.getId()).toUri());
		 return new ResponseEntity(headers, HttpStatus.CREATED);
	 }
}
