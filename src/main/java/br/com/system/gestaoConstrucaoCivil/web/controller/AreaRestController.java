package br.com.system.gestaoConstrucaoCivil.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.Area;
import br.com.system.gestaoConstrucaoCivil.service.AreaService;

@RestController
@RequestMapping("/rest/almoxarifado/area")
public class AreaRestController  {

	@Autowired 
	private AreaService areaService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/lista")
	public ResponseEntity<Iterable<Area>> buscarAreas()
	{
		Iterable<Area> area = areaService.buscarTodos();
		return new ResponseEntity<Iterable<Area>>(area, HttpStatus.OK);
	}
	@RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
	public ResponseEntity<Area> buscarAreaPorId(Long id)
	{
		return new ResponseEntity<Area>(areaService.buscarPorId(id),HttpStatus.OK);
	}
	public ResponseEntity salvarArea(@RequestBody Area area,UriComponentsBuilder ucBuilder)
	{
		areaService.salvarOuEditar(area);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("rest/almoxarifado/area/salva/{id}").buildAndExpand(area.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/altera", method = RequestMethod.PUT)
	public ResponseEntity alterarArea(@RequestBody Area area, UriComponentsBuilder ucBuilder) {
		areaService.salvarOuEditar(area);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("rest/almoxarifado/area/altera/{area}").buildAndExpand(area.getId()).toUri());
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
}
