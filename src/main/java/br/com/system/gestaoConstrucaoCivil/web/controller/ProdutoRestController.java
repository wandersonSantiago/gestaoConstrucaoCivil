package br.com.system.gestaoConstrucaoCivil.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.Produto;
import br.com.system.gestaoConstrucaoCivil.service.ProdutoService;

@RestController
@RequestMapping("/rest/produto/cadastrarProduto")
public class ProdutoRestController {

	 @Autowired
	 private ProdutoService produtoService;
	
	
	 @RequestMapping(method = RequestMethod.GET, value="/listarProduto")
	 public ResponseEntity<Iterable<Produto>> buscarProduto() {	  
	  
	  Iterable<Produto> produto = produtoService.buscarTodos();
	  return new ResponseEntity<Iterable<Produto>>(produto, HttpStatus.OK);
	 }
	 
	 @RequestMapping(method = RequestMethod.POST)
	 public ResponseEntity salva(@RequestBody Produto produto,UriComponentsBuilder ucBuilder)
	 {
		 produtoService.salvarOuEditar(produto);
		 HttpHeaders headers = new HttpHeaders();
		 headers.setLocation(ucBuilder.path("/rest/produto/cadastrarProduto/{id}").buildAndExpand(produto.getId()).toUri());
		 return new ResponseEntity(headers, HttpStatus.CREATED);
	 }
}
