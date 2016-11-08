package br.com.system.gestaoConstrucaoCivil.web.controller.almoxarifado;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Cotacao;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.View;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.CotacaoService;

@RestController
@RequestMapping("/rest/almoxarifado/cotacao")
public class CotacaoRestController {
	
	@Autowired
	private CotacaoService cotacaoService;
	
	@RequestMapping(value = "/salva", method = RequestMethod.POST)
	public ResponseEntity<Cotacao> salva(@RequestBody Cotacao cotacao){
	
		cotacaoService.salvaAltera(cotacao);
		HttpHeaders headers =  new HttpHeaders();
		return new ResponseEntity(headers, HttpStatus.CREATED);				
	}
	
	@JsonView(View.Summary.class)
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public ResponseEntity<Collection<Cotacao>> lista(){
		
		Collection<Cotacao> cotacao = cotacaoService.buscarTodos(); 
		return new ResponseEntity<Collection<Cotacao>>(cotacao, HttpStatus.OK);
	}
	@JsonView(View.Summary.class)
	@RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cotacao> buscarCotacaoPorId(@PathVariable Long id) {

		return new ResponseEntity<Cotacao>(cotacaoService.buscaPorId(id), HttpStatus.OK);
	}

}
