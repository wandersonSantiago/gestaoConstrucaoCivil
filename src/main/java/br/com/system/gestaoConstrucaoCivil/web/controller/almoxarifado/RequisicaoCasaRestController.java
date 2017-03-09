package br.com.system.gestaoConstrucaoCivil.web.controller.almoxarifado;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoCasa;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.RequisicaoCasaService;

@RestController
@RequestMapping("/rest/almoxarifado/estoque/requisicaoCasa")
public class RequisicaoCasaRestController {


	@Autowired
	private RequisicaoCasaService requisicaoCasaService;
	
	@PreAuthorize("hasAnyRole('ROLE_MODULO_ADMIN','ROLE_MODULO_REQUISICAO_CADASTRAR')")
	@PostMapping(value = "/salva")
	public ResponseEntity<RequisicaoCasa> salvarOuEditar(@RequestBody RequisicaoCasa requisicao,UriComponentsBuilder ucBuilder)
	{ 
		requisicaoCasaService.salvarOuEditar(requisicao);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_MODULO_ADMIN','ROLE_MODULO_REQUISICAO_CONSULTA_CASA')")
	@GetMapping(value = "/lista")
	public ResponseEntity<Collection<RequisicaoCasa>> buscarTodos()
	{
		return new ResponseEntity<Collection<RequisicaoCasa>>(requisicaoCasaService.buscarTodos(), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('ROLE_MODULO_ADMIN','ROLE_MODULO_REQUISICAO_CONSULTA_CASA')")
	@GetMapping(value = "/lista/paginacao")
	public ResponseEntity<Page<RequisicaoCasa>> lista(@RequestParam(defaultValue="0", required=false) int page
			,@RequestParam(defaultValue="0", required=false) int maxResults) {
		Page<RequisicaoCasa> objeto = requisicaoCasaService.buscarTodosComPaginacao(new PageRequest(page, maxResults));
		return new ResponseEntity<Page<RequisicaoCasa>>(objeto, HttpStatus.OK);
	}
	
	@PostMapping(value = "/aceitar")
	public ResponseEntity<RequisicaoCasa> aceitar(@RequestBody Integer numeroRequisicao)
	{
		requisicaoCasaService.aceitar(numeroRequisicao);
		return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
	}
	@PostMapping(value = "/rejeitar")
	public ResponseEntity<RequisicaoCasa> rejeitar(@RequestBody Integer numeroRequisicao)
	{
		requisicaoCasaService.rejeitar(numeroRequisicao);
		return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_MODULO_ADMIN','ROLE_MODULO_REQUISICAO_CONSULTA_CASA')")
	@RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
	public ResponseEntity<RequisicaoCasa> buscarPorId(@PathVariable Long id) {
		return new ResponseEntity<RequisicaoCasa>(requisicaoCasaService.buscarPorId(id), HttpStatus.OK);
	}
}
