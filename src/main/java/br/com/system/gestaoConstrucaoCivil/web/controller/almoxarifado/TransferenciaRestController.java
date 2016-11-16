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

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.PedidoCompra;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Transferencia;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.View;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.TransferenciaService;

@RestController
@RequestMapping("/rest/almoxarifado/transferencia")
public class TransferenciaRestController {
	
	@Autowired
	private TransferenciaService transferenciaService;
	
	@RequestMapping(value = "/salva", method = RequestMethod.POST)
	public ResponseEntity<Collection<Transferencia>> salva(@RequestBody Transferencia transferencia){
		transferenciaService.salvaAltera(transferencia);
		HttpHeaders headers =  new HttpHeaders();
		return new ResponseEntity(headers, HttpStatus.CREATED);				
	}
	@JsonView(View.Summary.class)
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public ResponseEntity<Collection<Transferencia>> lista(){
		
		Collection<Transferencia> transferencia = transferenciaService.buscarTodos(); 
		return new ResponseEntity<Collection<Transferencia>>(transferencia, HttpStatus.OK);
	}
	
	@JsonView(View.Summary.class)
	@RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
	public ResponseEntity<Transferencia> buscarCotacaoPorId(@PathVariable Long id) {

		return new ResponseEntity<Transferencia>(transferenciaService.buscaPorId(id), HttpStatus.OK);
	}

	@JsonView(View.Summary.class)
	@RequestMapping(value = "/recebida", method = RequestMethod.GET)
	public ResponseEntity<Collection<Transferencia>> buscarTransferenciaRecebida() {
		
    	return new ResponseEntity<Collection<Transferencia>>(transferenciaService.buscarTransferenciaRecebida(), HttpStatus.OK);
	}
	@JsonView(View.Summary.class)
	@RequestMapping(value = "/enviada", method = RequestMethod.GET)
	public ResponseEntity<Collection<Transferencia>>  buscarTransferenciaEnviada() {
		
		return new ResponseEntity<Collection<Transferencia>>(transferenciaService.buscarTransferenciaEnviada(), HttpStatus.OK);
	}

}
