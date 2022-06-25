package br.com.app.estoque.api.v1.resource;

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

import br.com.app.estoque.domain.model.Transferencia;
import br.com.app.estoque.domain.service.TransferenciaService;

@RestController
@RequestMapping("/rest/almoxarifado/transferencia")
public class TransferenciaResource {

	@Autowired
	private TransferenciaService transferenciaService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void salvar(@RequestBody Transferencia transferencia) {
		transferenciaService.salvarAlterar(transferencia);

	}

	@GetMapping(value = "/buscar")
	public ResponseEntity<Page<Transferencia>> findByDescricao(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="statusTransferencia") String orderBy, 
			@RequestParam(value="direction", defaultValue="DESC") String direction,
			@RequestParam(value="descricao", required = false , defaultValue="")String descricao,
			@RequestParam(value="tipo", required = true) String tipo) {

		Page<Transferencia> list = null;
		
		if(descricao.isEmpty() || descricao.equalsIgnoreCase("")) {
			list = transferenciaService.findAll(tipo,PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}else {
			list = transferenciaService.findByCodigo(tipo,descricao, PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy));
		}		
		return ResponseEntity.ok().body(list);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}")
	public Optional<Transferencia> findById(@PathVariable Long id) {
		return transferenciaService.buscaPorId(id);
	}



	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/aceitar")
	public void aceitar(@RequestBody Long numeroNota) {
		transferenciaService.aceitarTransferencia(numeroNota);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/rejeitar")
	public void rejeitar(@RequestBody Long numeroNota) {
		transferenciaService.rejeitarTransferencia(numeroNota);

	}
}
