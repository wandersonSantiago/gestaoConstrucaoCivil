package br.com.system.gestaoConstrucaoCivil.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.gestaoConstrucaoCivil.entity.AreaProduto;
import br.com.system.gestaoConstrucaoCivil.service.AreaService;

@RestController
@RequestMapping("/rest/almoxarifado/areaProduto")
public class AreaRestController {

	@Autowired
	private AreaService areaService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AreaProduto> buscarTodos() {

		return areaService.buscarTodos();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscaPorId/{id}")
	public AreaProduto buscarPorId(@PathVariable Long id) {

		return areaService.buscarPorId(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salva")
	public void salvar(@RequestBody AreaProduto areaProduto) {
		areaService.salvarOuEditar(areaProduto);
		 
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/altera")
	public void alterar(@RequestBody AreaProduto areaProduto) {
		areaService.salvarOuEditar(areaProduto);
	}
}
