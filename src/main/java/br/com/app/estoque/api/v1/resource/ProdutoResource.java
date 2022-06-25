package br.com.app.estoque.api.v1.resource;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.estoque.domain.enuns.UnidadeMedidaEnum;
import br.com.app.estoque.domain.model.Produto;
import br.com.app.estoque.domain.service.ProdutoService;

@RestController
@RequestMapping("/rest/estoque/produto")
public class ProdutoResource {

	@Autowired
	private ProdutoService produtoService;


	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}")
	public Produto buscarPorId(@PathVariable Long id) {
		return produtoService.buscaPorId(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscaPorCodigo/{codigo}")
	public Produto buscarPorCodigo(@PathVariable String codigo) {
		return produtoService.buscarPorCodigoOuCodigoBarra(codigo);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void salvar(@RequestBody Produto produto) {
		produtoService.salvarOuEditar(produto);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping
	public void alterar(@RequestBody Produto produto) {
		produtoService.salvarOuEditar(produto);
	}
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/unidades-medida")
	public Collection<UnidadeMedidaEnum> unidadeMedida() {
		return Arrays.asList(UnidadeMedidaEnum.values());
	}
	
	@GetMapping(value = "/descricao")
	public ResponseEntity<Page<Produto>> findByDescricao(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="descricao") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction,
			@RequestParam(value="descricao", required = false , defaultValue="")String descricao) {

		Page<Produto> list = null;
		
		if(descricao.isEmpty() || descricao.equalsIgnoreCase("")) {
			list = produtoService.findAll(PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}else {
			list = produtoService.findByDescricaoIgnoreCase(descricao, PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}
		
		return ResponseEntity.ok().body(list);
	}
}
