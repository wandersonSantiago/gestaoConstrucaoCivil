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

import br.com.system.gestaoConstrucaoCivil.entity.Fornecedor;
import br.com.system.gestaoConstrucaoCivil.entity.Produto;
import br.com.system.gestaoConstrucaoCivil.service.ProdutoService;

@RestController
@RequestMapping("/rest/almoxarifado/produto")
public class ProdutoRestController {

	 @Autowired
	 private ProdutoService produtoService;
	
	
	 @RequestMapping(method = RequestMethod.GET, value="/lista")
	 public ResponseEntity<Iterable<Produto>> buscarProduto() {	  
	  
	  Iterable<Produto> produto = produtoService.buscarTodos();
	  return new ResponseEntity<Iterable<Produto>>(produto, HttpStatus.OK);
	 }
	 @RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
		public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {

			return new ResponseEntity<Produto>(produtoService.buscaProdutoPorId(id), HttpStatus.OK);
		}

	 
	 @RequestMapping(value="/salva", method = RequestMethod.POST)
	 public ResponseEntity salva(@RequestBody Produto produto,UriComponentsBuilder ucBuilder)
	 {
		 for(int i = 0 ; i < produto.getFornecedores().size(); i++ ){
			 System.out.println(produto.getFornecedores().get(i).getContato());
			 System.out.println(produto.getDescricao());
		 }
		
		 produtoService.salvarOuEditar(produto);
		 HttpHeaders headers = new HttpHeaders();
		 headers.setLocation(ucBuilder.path("/rest/almoxarifado/produto/salva/{id}").buildAndExpand(produto.getId()).toUri());
		 return new ResponseEntity(headers, HttpStatus.CREATED);
	 }
	 
	 @RequestMapping(value="/altera", method = RequestMethod.PUT)
	 public ResponseEntity altera(@RequestBody Produto produto,UriComponentsBuilder ucBuilder)
	 { produtoService.salvarOuEditar(produto);
		 HttpHeaders headers = new HttpHeaders();
		 headers.setLocation(ucBuilder.path("/rest/almoxarifado/produto/altera/{id}").buildAndExpand(produto.getId()).toUri());
		 return new ResponseEntity(headers, HttpStatus.CREATED);
	 }
}
