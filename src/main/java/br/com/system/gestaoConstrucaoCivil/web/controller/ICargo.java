package br.com.system.gestaoConstrucaoCivil.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.Cargo;

public interface ICargo {

	@PostMapping(value = "/salva")
	public ResponseEntity<Cargo> salva(@RequestBody Cargo cargo,UriComponentsBuilder ucBuilder);
	
	@GetMapping(value="/lista")
	public ResponseEntity<Iterable<Cargo>> buscarCargos();
	
    @GetMapping(value = "/buscaPorId/{id}")
	public ResponseEntity<Cargo> buscarCargoPorId(@PathVariable Long id) ;
	
	@PutMapping(value = "/altera")
	public ResponseEntity<Cargo> updateCargo(@RequestBody Cargo cargo,UriComponentsBuilder ucBuilder);
}
