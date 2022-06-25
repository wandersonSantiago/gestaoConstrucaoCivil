package br.com.app.commons.api.v1.resource;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.commons.domain.model.Estrutura;
import br.com.app.commons.domain.service.EstruturaService;

@RestController
@RequestMapping("rest/estrutura")
public class EstruturaResource {

	@Autowired
	private EstruturaService estruturaService;


	@GetMapping(value = "/descricao")
	public ResponseEntity<Page<Estrutura>> findByDescricao(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="descricao") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction,
			@RequestParam(value="descricao", required = false , defaultValue="")String descricao) {

		Page<Estrutura> list = null;
		
		if(descricao.isEmpty() || descricao.equalsIgnoreCase("")) {
			list = estruturaService.findAll(PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}else {
			list = estruturaService.findByDescricaoIgnoreCase(descricao, PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}
		
		return ResponseEntity.ok().body(list);
	}

		
	@GetMapping(value = "/{id}")
	public ResponseEntity<Estrutura> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(estruturaService.findById(id));
	}

	@GetMapping(value = "/folhas/{id}")
	public ResponseEntity<List<Estrutura>> findByEstruturasFolhas(@PathVariable Long id) {
		List<Estrutura> estrutura = estruturaService.findByEstruturasFolhas(id);
		return ResponseEntity.ok().body(estrutura);
	}
	
	@PostMapping
	public Estrutura insert(@RequestBody Estrutura estrutura) {

		return estruturaService.insert(estrutura);
	}
	
	
}
