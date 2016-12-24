package br.com.system.gestaoConstrucaoCivil.web.controller.almoxarifado;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Cotacao;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.CotacaoService;

@RestController
@RequestMapping("/rest/almoxarifado/cotacao")
public class CotacaoRestController {
	
	@Autowired
	private CotacaoService cotacaoService;
	
	@PostMapping(value = "/salva")
	public ResponseEntity<Cotacao> salvar(@RequestBody Cotacao cotacao){
	
		cotacaoService.salvaAltera(cotacao);
		HttpHeaders headers =  new HttpHeaders();
		return new ResponseEntity(headers, HttpStatus.CREATED);				
	}
	
	@GetMapping(value = "/lista")
	public ResponseEntity<Collection<Cotacao>> buscarTodos(){
		
		return new ResponseEntity<Collection<Cotacao>>(cotacaoService.buscarTodos(), HttpStatus.OK);
	}
	@GetMapping(value = "/buscaPorId/{id}")
	public ResponseEntity<Cotacao> buscarPorId(@PathVariable Long id) {

		return new ResponseEntity<Cotacao>(cotacaoService.buscaPorId(id), HttpStatus.OK);
	}
	@GetMapping(value = "/fecharCotacao")
	public ResponseEntity<Cotacao> fecharCotacao(Long idCotacao){
		
	    cotacaoService.fecharCotacao(idCotacao);
		return new ResponseEntity(new HttpHeaders(), HttpStatus.CREATED);	
	}

}
