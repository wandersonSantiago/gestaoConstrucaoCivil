package br.com.system.gestaoConstrucaoCivil.web.controller.almoxarifado;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoOutros;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.RequisicaoOutrosService;

@RestController
@RequestMapping("/rest/almoxarifado/estoque/requisicaoOutros")
public class RequisicaoOutrosRestController {


	@Autowired
	private RequisicaoOutrosService requisicaoOutrosService;
	
	
	@PostMapping(value = "/salva")
	public ResponseEntity<RequisicaoOutros> salvarOuEditar(@RequestBody RequisicaoOutros requisicao,UriComponentsBuilder ucBuilder)
	{ 
		requisicaoOutrosService.salvarOuEditar(requisicao);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/lista")
	public ResponseEntity<Collection<RequisicaoOutros>> buscarTodos()
	{
		Collection<RequisicaoOutros> requisicao = requisicaoOutrosService.buscarTodos(); 
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Collection<RequisicaoOutros>>(requisicao, HttpStatus.OK);
	}
	@PostMapping(value = "/aceitar")
	public ResponseEntity<RequisicaoOutros> aceitar(@RequestBody Integer numeroRequisicao)
	{
		requisicaoOutrosService.aceitar(numeroRequisicao);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity(headers, HttpStatus.OK);
	}
	@PostMapping(value = "/rejeitar")
	public ResponseEntity<RequisicaoOutros> rejeitar(@RequestBody Integer numeroRequisicao)
	{
		requisicaoOutrosService.rejeitar(numeroRequisicao);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity(headers, HttpStatus.OK);
	}
}
