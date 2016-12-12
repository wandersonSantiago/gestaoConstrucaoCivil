package br.com.system.gestaoConstrucaoCivil.web.controller.almoxarifado;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Transferencia;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.View;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.TransferenciaService;

@RestController
@RequestMapping("/rest/almoxarifado/transferencia")
public class TransferenciaRestController {
	
	@Autowired
	private TransferenciaService transferenciaService;
	
	@RequestMapping(value = "/salva", method = RequestMethod.POST)
	public ResponseEntity<Collection<Transferencia>> salvar(@RequestBody Transferencia transferencia){
		transferenciaService.salvarAlterar(transferencia);
		HttpHeaders headers =  new HttpHeaders();
		return new ResponseEntity(headers, HttpStatus.CREATED);				
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public ResponseEntity<Collection<Transferencia>> buscarTodos(){
		
		Collection<Transferencia> transferencia = transferenciaService.buscarTodos(); 
		return new ResponseEntity<Collection<Transferencia>>(transferencia, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
	public ResponseEntity<Transferencia> buscarPorId(@PathVariable Long id) {

		return new ResponseEntity<Transferencia>(transferenciaService.buscaPorId(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/recebida", method = RequestMethod.GET)
	public ResponseEntity<Collection<Transferencia>> buscarTransferenciaRecebida() {
		
    	return new ResponseEntity<Collection<Transferencia>>(transferenciaService.buscarTransferenciaRecebida(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/enviada", method = RequestMethod.GET)
	public ResponseEntity<Collection<Transferencia>>  buscarTransferenciaEnviada() {
		
		return new ResponseEntity<Collection<Transferencia>>(transferenciaService.buscarTransferenciaEnviada(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/aceitar", method = RequestMethod.POST)
	public ResponseEntity<Collection<Transferencia>> aceitar(@RequestBody Long numeroNota){
		transferenciaService.aceitarTransferencia(numeroNota);
		HttpHeaders headers =  new HttpHeaders();
		return new ResponseEntity(headers, HttpStatus.CREATED);				
	}
	
	@RequestMapping(value = "/rejeitar", method = RequestMethod.POST)
	public ResponseEntity<Collection<Transferencia>> rejeitar(@RequestBody Long numeroNota){
		transferenciaService.rejeitarTransferencia(numeroNota);
		HttpHeaders headers =  new HttpHeaders();
		return new ResponseEntity(headers, HttpStatus.CREATED);				
	}
}
