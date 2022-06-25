package br.com.app.servico.api.v1.resource;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.servico.domain.model.PrestadoraServico;
import br.com.app.servico.domain.service.PrestadoraServicoService;

@RestController
@RequestMapping("/rest/servicos/prestadoraServico")
public class PrestadoraResource {

	@Autowired
	private PrestadoraServicoService prestadoraServicoService;

	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}")
	public PrestadoraServico buscarPorId(@PathVariable Long id) {
		return prestadoraServicoService.findById(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void insert(@RequestBody PrestadoraServico prestadoraServico) {

		prestadoraServicoService.insert(prestadoraServico);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping
	public void update(@RequestBody PrestadoraServico prestadoraServico) {

		prestadoraServicoService.insert(prestadoraServico);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/cnpj/{cnpj}")
	public Optional<PrestadoraServico> buscarPorCnpj(@PathVariable String cnpj) {
		return prestadoraServicoService.buscarPorCNPJ(cnpj);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/existeCnpj/{cnpj}")
	public Boolean existeCnpj(@PathVariable String cnpj) {
		return prestadoraServicoService.existeCnpjCadastrado(cnpj);
	}
	
	@GetMapping(value = "/buscar")
	public ResponseEntity<Page<PrestadoraServico>> findByDescricao(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="dadoEmpresa.nomeFantasia") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction,
			@RequestParam(value="descricao", required = false , defaultValue="")String descricao) {

		Page<PrestadoraServico> list = null;
		
		if(descricao.isEmpty() || descricao.equalsIgnoreCase("")) {
			list = prestadoraServicoService.findAll(PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}else {
			list = prestadoraServicoService.findByDescricaoIgnoreCase(descricao, PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}
		
		return ResponseEntity.ok().body(list);
	}
}
