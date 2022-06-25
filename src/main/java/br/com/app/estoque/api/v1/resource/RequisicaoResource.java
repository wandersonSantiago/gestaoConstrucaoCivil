package br.com.app.estoque.api.v1.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.estoque.domain.model.Requisicao;
import br.com.app.estoque.domain.service.RequisicaoService;

@RestController
@RequestMapping("/rest/almoxarifado/requisicao")
public class RequisicaoResource {

	@Autowired
	private RequisicaoService requisicaoService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void insert(@RequestBody Requisicao requisicao) {
		requisicaoService.insert(requisicao);

	}

	@GetMapping(value = "/buscar")
	public ResponseEntity<Page<Requisicao>> findByDescricao(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="id") String orderBy, 
			@RequestParam(value="direction", defaultValue="DESC") String direction,
			@RequestParam(value="descricao", required = false , defaultValue="")String descricao,
			@RequestParam(value="tipo", defaultValue="LIBERAR",required = true) String tipo) {

		Page<Requisicao> list = null;
		
		if(descricao.isEmpty() || descricao.equalsIgnoreCase("")) {
			list = requisicaoService.findAll(tipo,PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}else {
			list = requisicaoService.findByCodigo(tipo,descricao, PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}		
		return ResponseEntity.ok().body(list);
	}
	
	
		
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/aceitar")
	public void aceitar(@RequestBody Long idRequisicao) {
		requisicaoService.aceitar(idRequisicao);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/rejeitar")
	public void rejeitar(@RequestBody Long idRequisicao) {		 
		requisicaoService.rejeitar(idRequisicao);

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}")
	public Requisicao buscarPorId(@PathVariable Long id) {
		return requisicaoService.findByIdAndEmpreendimentoId(id);
	}

}
