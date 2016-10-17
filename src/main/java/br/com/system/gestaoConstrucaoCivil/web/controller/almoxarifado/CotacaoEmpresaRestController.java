package br.com.system.gestaoConstrucaoCivil.web.controller.almoxarifado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.CotacaoEmpresa;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.CotacaoEmpresaService;

@RestController
@RequestMapping("/rest/almoxarifado/estoque/cotacao")
public class CotacaoEmpresaRestController {

	@Autowired
	private CotacaoEmpresaService cotacaoEmpresaService;
	
	@RequestMapping(value = "/salva", method = RequestMethod.POST)
	public ResponseEntity<CotacaoEmpresa> salva(@RequestBody CotacaoEmpresa cotacaoEmpresa){
		cotacaoEmpresaService.salvarOuEditar(cotacaoEmpresa);
		HttpHeaders headers =  new HttpHeaders();
		return new ResponseEntity(headers, HttpStatus.CREATED);				
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public ResponseEntity<List<CotacaoEmpresa>> lista(){
		
		List<CotacaoEmpresa> cotacaoEmpresa = cotacaoEmpresaService.buscarTodos(); 
		return new ResponseEntity<List<CotacaoEmpresa>>(cotacaoEmpresa, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
	public ResponseEntity<CotacaoEmpresa> buscarPorId(@PathVariable Long id) {

		return new ResponseEntity<CotacaoEmpresa>(cotacaoEmpresaService.buscarPorId(id), HttpStatus.OK);
	}
}
