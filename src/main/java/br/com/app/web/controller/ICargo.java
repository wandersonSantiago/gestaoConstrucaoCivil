package br.com.app.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.app.entity.Cargo;

public interface ICargo {

	
	public ResponseEntity<Cargo> salva(@RequestBody Cargo cargo,UriComponentsBuilder ucBuilder);
	
	
	public ResponseEntity<Iterable<Cargo>> buscarCargos();
	
   
	public ResponseEntity<Cargo> buscarCargoPorId(@PathVariable Long id) ;
	
	
	public ResponseEntity<Cargo> updateCargo(@RequestBody Cargo cargo,UriComponentsBuilder ucBuilder);
}
