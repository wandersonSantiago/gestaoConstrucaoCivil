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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.NotaFiscal;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoCasa;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.RequisicaoCasaService;

@RestController
@RequestMapping("/rest/almoxarifado/estoque/requisicaoCasa")
public class RequisicaoCasaRestController {


	@Autowired
	private RequisicaoCasaService requisicaoCasaService;
	
	@PostMapping(value = "/salva")
	public ResponseEntity<RequisicaoCasa> salvarOuEditar(@RequestBody RequisicaoCasa requisicao,UriComponentsBuilder ucBuilder)
	{ 
		requisicaoCasaService.salvarOuEditar(requisicao);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/lista")
	public ResponseEntity<Collection<RequisicaoCasa>> buscarTodos()
	{
		Collection<RequisicaoCasa> requisicao = requisicaoCasaService.buscarTodos(); 
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Collection<RequisicaoCasa>>(requisicao, HttpStatus.OK);
	}
	@PostMapping(value = "/aceitar")
	public ResponseEntity<RequisicaoCasa> aceitar(@RequestBody Integer numeroRequisicao)
	{
		requisicaoCasaService.aceitar(numeroRequisicao);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity(headers, HttpStatus.OK);
	}
	@PostMapping(value = "/rejeitar")
	public ResponseEntity<RequisicaoCasa> rejeitar(@RequestBody Integer numeroRequisicao)
	{
		requisicaoCasaService.rejeitar(numeroRequisicao);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity(headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
	public ResponseEntity<RequisicaoCasa> buscarPorId(@PathVariable Long id) {
		return new ResponseEntity<RequisicaoCasa>(requisicaoCasaService.buscarPorId(id), HttpStatus.OK);
	}
}
