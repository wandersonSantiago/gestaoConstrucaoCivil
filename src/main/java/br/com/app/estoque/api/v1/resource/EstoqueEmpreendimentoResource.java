package br.com.app.estoque.api.v1.resource;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.estoque.domain.model.EstoqueEmpreendimento;
import br.com.app.estoque.domain.service.EstoqueEmpreendimentoService;

@RestController
@RequestMapping("/rest/estoque")
public class EstoqueEmpreendimentoResource {

	@Autowired
	private EstoqueEmpreendimentoService estoqueService;

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping
	public void updateConfiguration(@RequestBody EstoqueEmpreendimento estoqueEmpreendimento) {
		estoqueService.updateConfiguration(estoqueEmpreendimento);

	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/baixo")
	public Collection<EstoqueEmpreendimento> produtoEstoqueBaixo() {
		return estoqueService.produtoEstoqueBaixo();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/alto")
	public Collection<EstoqueEmpreendimento> produtoEstoqueAlto() {
		return estoqueService.produtoEstoqueAlto();
	}

	
	@GetMapping(value = "/buscar")
	public ResponseEntity<Page<EstoqueEmpreendimento>> findByDescricao(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="produto.descricao") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction,
			@RequestParam(value="descricao", required = false , defaultValue="")String descricao) {

		Page<EstoqueEmpreendimento> list = null;
		
		if(descricao.isEmpty() || descricao.equalsIgnoreCase("")) {
			list = estoqueService.findAll(PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}else {
			list = estoqueService.findByDescricaoIgnoreCase(descricao, PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}
		
		return ResponseEntity.ok().body(list);
	}

	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}")
	public  EstoqueEmpreendimento buscarPorCodigo(@PathVariable Long id) {
		return estoqueService.findById(id);

	}
}
