package br.com.system.gestaoConstrucaoCivil.web.controller.almoxarifado;

import java.util.List;

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

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.CotacaoEmpresa;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.CotacaoEmpresaService;

@RestController
@RequestMapping("/rest/almoxarifado/estoque/cotacao")
public class CotacaoEmpresaRestController {

	@Autowired
	private CotacaoEmpresaService cotacaoEmpresaService;
	
	@PostMapping(value = "/salva")
	public ResponseEntity<CotacaoEmpresa> salvar(@RequestBody CotacaoEmpresa cotacaoEmpresa){
		cotacaoEmpresaService.salvarOuEditar(cotacaoEmpresa);
		HttpHeaders headers =  new HttpHeaders();
		return new ResponseEntity(headers, HttpStatus.CREATED);				
	}
	
	@GetMapping(value = "/lista")
	public ResponseEntity<List<CotacaoEmpresa>> buscarTodos(){
		
		return new ResponseEntity<List<CotacaoEmpresa>>(cotacaoEmpresaService.buscarTodos(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/buscaPorId/{id}")
	public ResponseEntity<CotacaoEmpresa> buscarPorId(@PathVariable Long id) {

		return new ResponseEntity<CotacaoEmpresa>(cotacaoEmpresaService.buscarPorId(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/buscaGanhdores/{idCotacao}")
	public ResponseEntity<List<CotacaoEmpresa>> buscarGanhadores(@PathVariable Long idCotacao) {

		return new ResponseEntity<List<CotacaoEmpresa>>(cotacaoEmpresaService.ganhadores(idCotacao), HttpStatus.OK);
	} 
	
	@GetMapping(value = "/concorrentes/{idCotacao}")
	public ResponseEntity<List<CotacaoEmpresa>> buscarConcorrentes(@PathVariable Long idCotacao) {

		return new ResponseEntity<List<CotacaoEmpresa>>(cotacaoEmpresaService.concorrentes(idCotacao) , HttpStatus.OK);
	} 
}
