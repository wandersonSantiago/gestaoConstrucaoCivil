package br.com.system.gestaoConstrucaoCivil.web.controller.almoxarifado;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Requisicao;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.RequisicaoService;

@RestController
@RequestMapping("/rest/almoxarifado/estoque/requisicao")
public class RequisicaoRestController {

	@Autowired
	private RequisicaoService requisicaoService;
	
	@RequestMapping(value = "/salva", method = RequestMethod.POST)
	public ResponseEntity salvar(@RequestBody List<Requisicao> requisicao,UriComponentsBuilder ucBuilder)
	{ 
		requisicaoService.salvarOuEditar(requisicao);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	@RequestMapping(method = RequestMethod.GET, value = "/lista")
	public ResponseEntity<Iterable<Requisicao>> buscarTodos()
	{
		Iterable<Requisicao> baixaEstoque = requisicaoService.buscarTodos(); 
		return new ResponseEntity<Iterable<Requisicao>>(baixaEstoque, HttpStatus.OK);
	}
}
