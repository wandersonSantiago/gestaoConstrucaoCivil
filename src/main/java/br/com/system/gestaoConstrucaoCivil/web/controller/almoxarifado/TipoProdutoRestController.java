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

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.TipoProduto;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.TipoProdutoService;

@RestController
@RequestMapping(value = "/rest/almoxarifado/produto/tipo")
public class TipoProdutoRestController {
	
	@Autowired
	private TipoProdutoService tipoProdutoService;
	

	 @RequestMapping(method = RequestMethod.GET, value="/lista")
	 public ResponseEntity<Iterable<TipoProduto>> buscarTodos() {	  
	  Iterable<TipoProduto> tipoProduto = tipoProdutoService.buscarTodos();
	  return new ResponseEntity<Iterable<TipoProduto>>(tipoProduto, HttpStatus.OK);
	 }
	
	 @RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
		public ResponseEntity<TipoProduto> buscarPorId(@PathVariable Long id) {
		return new ResponseEntity<TipoProduto>(tipoProdutoService.buscaPorId(id), HttpStatus.OK);
		}

	 
	 @RequestMapping(value="/salva", method = RequestMethod.POST)
	 public ResponseEntity salvar(@RequestBody TipoProduto tipoProduto,UriComponentsBuilder ucBuilder)
	 {	 tipoProdutoService.salvaAltera(tipoProduto);
		 HttpHeaders headers = new HttpHeaders();
		 headers.setLocation(ucBuilder.path("/rest/almoxarifado/produto/tipo/salva/{id}").buildAndExpand(tipoProduto.getId()).toUri());
		 return new ResponseEntity(headers, HttpStatus.CREATED);
	 }
	 
	 @RequestMapping(value="/altera", method = RequestMethod.PUT)
	 public ResponseEntity alterar(@RequestBody TipoProduto tipoProduto,UriComponentsBuilder ucBuilder)
	 {
		 tipoProdutoService.salvaAltera(tipoProduto);
		 HttpHeaders headers = new HttpHeaders();
		 headers.setLocation(ucBuilder.path("/rest/almoxarifado/produto/tipo/altera/{id}").buildAndExpand(tipoProduto.getId()).toUri());
		 return new ResponseEntity(headers, HttpStatus.CREATED);
	 }
	 

}
