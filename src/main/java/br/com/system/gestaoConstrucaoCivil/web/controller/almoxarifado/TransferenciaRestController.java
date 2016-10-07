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

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.PedidoCompra;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Transferencia;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.TransferenciaService;

@RestController
@RequestMapping("/rest/almoxarifado/transferencia")
public class TransferenciaRestController {
	
	@Autowired
	private TransferenciaService TransferenciaService;
	
	@RequestMapping(value = "/salva", method = RequestMethod.POST)
	public ResponseEntity<Collection<Transferencia>> salva(@RequestBody Transferencia transferencia){
		TransferenciaService.salvaAltera(transferencia);
		HttpHeaders headers =  new HttpHeaders();
		return new ResponseEntity(headers, HttpStatus.CREATED);				
	}
	
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public ResponseEntity<Collection<Transferencia>> lista(){
		
		Collection<Transferencia> transferencia = TransferenciaService.buscarTodos(); 
		return new ResponseEntity<Collection<Transferencia>>(transferencia, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
	public ResponseEntity<Transferencia> buscarCotacaoPorId(@PathVariable Long id) {

		return new ResponseEntity<Transferencia>(TransferenciaService.buscaPorId(id), HttpStatus.OK);
	}


}
