package br.com.app.web.controller;


import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.app.entity.Empreendimento;
import br.com.app.enuns.StatusEmpreendimento;
import br.com.app.service.EmpreendimentoService;

@RestController
@RequestMapping("rest/empreendimento")
public class EmpreendimentoRestController {

	@Autowired
	private EmpreendimentoService empreendimentoService;


	@GetMapping(value = "/descricao")
	public ResponseEntity<Page<Empreendimento>> findByDescricao(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="descricao") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction,
			@RequestParam(value="descricao", required = false , defaultValue="")String descricao) {

		Page<Empreendimento> list = null;
		
		if(descricao.isEmpty() || descricao.equalsIgnoreCase("")) {
			list = empreendimentoService.findAll(PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}else {
			list = empreendimentoService.findByDescricaoIgnoreCase(descricao, PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}
		
		return ResponseEntity.ok().body(list);
	}

		
	@GetMapping(value = "/{id}")
	public ResponseEntity<Empreendimento> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok().body(empreendimentoService.findById(id));
	}

	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Empreendimento empreendimento) {

		 empreendimento = empreendimentoService.insert(empreendimento);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(empreendimento.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/status")
	public Collection<StatusEmpreendimento> status() {
		return Arrays.asList(StatusEmpreendimento.values());
	}

}
