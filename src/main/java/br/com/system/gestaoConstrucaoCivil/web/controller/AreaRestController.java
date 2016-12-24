package br.com.system.gestaoConstrucaoCivil.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.AreaProduto;
import br.com.system.gestaoConstrucaoCivil.service.AreaService;

@RestController
@RequestMapping("/rest/almoxarifado/areaProduto")
public class AreaRestController  {

	@Autowired 
	private AreaService areaService;
	
	@GetMapping(value = "/lista")
	public ResponseEntity<Iterable<AreaProduto>> buscarTodos()
	{
		return new ResponseEntity<Iterable<AreaProduto>>(areaService.buscarTodos(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/buscaPorId/{id}")
	public ResponseEntity<AreaProduto> buscarPorId(@PathVariable Long id) {

		return new ResponseEntity<AreaProduto>(areaService.buscarPorId(id), HttpStatus.OK);
	}
	
	@PostMapping(value = "/salva")
	public ResponseEntity<AreaProduto> salvar(@RequestBody AreaProduto areaProduto,UriComponentsBuilder ucBuilder)
	{
		areaService.salvarOuEditar(areaProduto);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("rest/almoxarifado/area/salva/{id}").buildAndExpand(areaProduto.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/altera")
	public ResponseEntity<AreaProduto> alterar(@RequestBody AreaProduto areaProduto, UriComponentsBuilder ucBuilder) {
		areaService.salvarOuEditar(areaProduto);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("rest/almoxarifado/area/altera/{area}").buildAndExpand(areaProduto.getId()).toUri());
		
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
}
