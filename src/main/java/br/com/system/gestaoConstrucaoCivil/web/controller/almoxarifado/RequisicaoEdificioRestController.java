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

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoEdificio;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.RequisicaoEdificioService;

@RestController
@RequestMapping("/rest/almoxarifado/estoque/requisicaoEdificio")
public class RequisicaoEdificioRestController {

	@Autowired
	private RequisicaoEdificioService requisicaoEdificioService;
	
	
	@PostMapping(value = "/salva")
	public ResponseEntity<RequisicaoEdificio> salvarOuEditar(@RequestBody RequisicaoEdificio requisicao,UriComponentsBuilder ucBuilder)
	{ 
		requisicaoEdificioService.salvarOuEditar(requisicao);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/lista")
	public ResponseEntity<Collection<RequisicaoEdificio>> buscarTodos()
	{
		Collection<RequisicaoEdificio> requisicao = requisicaoEdificioService.buscarTodos(); 
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Collection<RequisicaoEdificio>>(requisicao, HttpStatus.OK);
	}
	@PostMapping(value = "/aceitar")
	public ResponseEntity<RequisicaoEdificio> aceitar(@RequestBody Integer numeroRequisicao)
	{
		requisicaoEdificioService.aceitar(numeroRequisicao);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity(headers, HttpStatus.OK);
	}
	@PostMapping(value = "/rejeitar")
	public ResponseEntity<RequisicaoEdificio> rejeitar(@RequestBody Integer numeroRequisicao)
	{
		requisicaoEdificioService.rejeitar(numeroRequisicao);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity(headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
	public ResponseEntity<RequisicaoEdificio> buscarPorId(@PathVariable Long id) {
		return new ResponseEntity<RequisicaoEdificio>(requisicaoEdificioService.buscarPorId(id), HttpStatus.OK);
	}
}
