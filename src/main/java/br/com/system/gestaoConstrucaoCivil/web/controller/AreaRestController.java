package br.com.system.gestaoConstrucaoCivil.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.AreaProduto;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Fornecedor;
import br.com.system.gestaoConstrucaoCivil.service.AreaService;

@RestController
@RequestMapping("/rest/almoxarifado/areaProduto")
public class AreaRestController  {

	@Autowired 
	private AreaService areaService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/lista")
	public ResponseEntity<Iterable<AreaProduto>> buscarAreas()
	{
		Iterable<AreaProduto> areaProduto = areaService.buscarTodos();
		return new ResponseEntity<Iterable<AreaProduto>>(areaProduto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
	public ResponseEntity<AreaProduto> buscarPorId(@PathVariable Long id) {

		return new ResponseEntity<AreaProduto>(areaService.buscarPorId(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/salva", method = RequestMethod.POST)
	public ResponseEntity salvarArea(@RequestBody AreaProduto areaProduto,UriComponentsBuilder ucBuilder)
	{
		areaService.salvarOuEditar(areaProduto);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("rest/almoxarifado/area/salva/{id}").buildAndExpand(areaProduto.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/altera", method = RequestMethod.PUT)
	public ResponseEntity alterarArea(@RequestBody AreaProduto areaProduto, UriComponentsBuilder ucBuilder) {
		areaService.salvarOuEditar(areaProduto);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("rest/almoxarifado/area/altera/{area}").buildAndExpand(areaProduto.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
}
