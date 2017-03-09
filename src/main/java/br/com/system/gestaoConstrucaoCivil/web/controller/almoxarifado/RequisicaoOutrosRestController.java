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

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoOutros;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.RequisicaoOutrosService;

@RestController
@RequestMapping("/rest/almoxarifado/estoque/requisicaoOutros")
public class RequisicaoOutrosRestController {


	@Autowired
	private RequisicaoOutrosService requisicaoOutrosService;
	
	@PreAuthorize("hasAnyRole('ROLE_MODULO_ADMIN','ROLE_MODULO_REQUISICAO_CADASTRAR')")
	@PostMapping(value = "/salva")
	public ResponseEntity<RequisicaoOutros> salvarOuEditar(@RequestBody RequisicaoOutros requisicao,UriComponentsBuilder ucBuilder)
	{ 
		requisicaoOutrosService.salvarOuEditar(requisicao);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_MODULO_ADMIN','ROLE_MODULO_REQUISICAO_CONSULTA_OUTROS')")
	@GetMapping(value = "/lista")
	public ResponseEntity<Collection<RequisicaoOutros>> buscarTodos()
	{
		return new ResponseEntity<Collection<RequisicaoOutros>>(requisicaoOutrosService.buscarTodos(), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('ROLE_MODULO_ADMIN','ROLE_MODULO_REQUISICAO_CONSULTA_OUTROS')")
	@GetMapping(value = "/lista/paginacao")
	public ResponseEntity<Page<RequisicaoOutros>> lista(@RequestParam(defaultValue="0", required=false) int page
			,@RequestParam(defaultValue="0", required=false) int maxResults) {
		Page<RequisicaoOutros> objeto = requisicaoOutrosService.buscarTodosComPaginacao(new PageRequest(page, maxResults));
		return new ResponseEntity<Page<RequisicaoOutros>>(objeto, HttpStatus.OK);
	}
	
	@PostMapping(value = "/aceitar")
	public ResponseEntity<RequisicaoOutros> aceitar(@RequestBody Integer numeroRequisicao)
	{
		requisicaoOutrosService.aceitar(numeroRequisicao);
		return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
	}
	@PostMapping(value = "/rejeitar")
	public ResponseEntity<RequisicaoOutros> rejeitar(@RequestBody Integer numeroRequisicao)
	{
		requisicaoOutrosService.rejeitar(numeroRequisicao);
		return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('ROLE_MODULO_ADMIN','ROLE_MODULO_REQUISICAO_CONSULTA_OUTROS')")
	@RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
	public ResponseEntity<RequisicaoOutros> buscarPorId(@PathVariable Long id) {
		return new ResponseEntity<RequisicaoOutros>(requisicaoOutrosService.buscarPorId(id), HttpStatus.OK);
	}
}
