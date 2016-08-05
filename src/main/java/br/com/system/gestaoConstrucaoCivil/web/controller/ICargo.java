package br.com.system.gestaoConstrucaoCivil.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.Cargo;

public interface ICargo {

	@RequestMapping(value = "/cadastrarCargo", method = RequestMethod.POST)
	public ResponseEntity salva(@RequestBody Cargo cargo,UriComponentsBuilder ucBuilder);
	
	@RequestMapping(method = RequestMethod.GET, value="/listarCargo")
	public ResponseEntity<Iterable<Cargo>> buscarCargos();
	
    @RequestMapping(value = "/listarCargoId/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cargo> buscarCargoPorId(@PathVariable Long id) ;
	
	@RequestMapping(value = "/editarCargo", method = RequestMethod.PUT)
	public ResponseEntity updateCargo(@RequestBody Cargo cargo,UriComponentsBuilder ucBuilder);
}
