package br.com.app.web.controller.almoxarifado;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.entity.almoxarifado.CotacaoEmpresa;
import br.com.app.service.almoxarifado.CotacaoEmpresaService;

@RestController
@RequestMapping("/rest/almoxarifado/cotacao/empresa")
public class CotacaoEmpresaRestController {

	@Autowired
	private CotacaoEmpresaService cotacaoEmpresaService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void salvar(@RequestBody CotacaoEmpresa cotacaoEmpresa){
		cotacaoEmpresaService.salvarOuEditar(cotacaoEmpresa);
		 				
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista")
	public Collection<CotacaoEmpresa> buscarTodos(){
		
		return  cotacaoEmpresaService.buscarTodos();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}")
	public Optional<CotacaoEmpresa> buscarPorId(@PathVariable Long id) {

		return cotacaoEmpresaService.buscarPorId(id);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscaGanhdores/{idCotacao}")
	public Collection<CotacaoEmpresa> buscarGanhadores(@PathVariable Long idCotacao) {

		return cotacaoEmpresaService.ganhadores(idCotacao);
	} 
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/concorrentes/{idCotacao}")
	public Collection<CotacaoEmpresa> buscarConcorrentes(@PathVariable Long idCotacao) {

		return  cotacaoEmpresaService.concorrentes(idCotacao);
	} 
}
