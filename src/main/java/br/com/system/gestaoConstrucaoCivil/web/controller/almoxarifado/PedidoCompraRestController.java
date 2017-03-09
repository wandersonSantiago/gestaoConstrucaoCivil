package br.com.system.gestaoConstrucaoCivil.web.controller.almoxarifado;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.PedidoCompra;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.PedidoCompraService;

@RestController
@RequestMapping("/rest/almoxarifado/pedidoCompra")
public class PedidoCompraRestController {
	
	@Autowired
	private PedidoCompraService pedidoCompraService;
	
	
	@PreAuthorize("hasAnyRole('ROLE_MODULO_ADMIN','ROLE_MODULO_COMPRAS_CADASTRAR')")
	@PostMapping(value = "/salva")
	public ResponseEntity<PedidoCompra> salvar(@RequestBody PedidoCompra pedidoCompra){
		pedidoCompraService.salvaAltera(pedidoCompra);
		HttpHeaders headers =  new HttpHeaders();
		return new ResponseEntity<>(headers, HttpStatus.CREATED);				
	}

	@PreAuthorize("hasAnyRole('ROLE_MODULO_ADMIN','ROLE_MODULO_COMPRAS_CONSULTAR')")
	@GetMapping(value = "/lista")
	public ResponseEntity<Collection<PedidoCompra>> buscarTodos(){
		
		return new ResponseEntity<Collection<PedidoCompra>>(pedidoCompraService.buscarTodos(), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_MODULO_ADMIN','ROLE_MODULO_COMPRAS_CONSULTAR')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<PedidoCompra> buscarPorId(@PathVariable Long id) {

		return new ResponseEntity<PedidoCompra>(pedidoCompraService.buscaPorId(id), HttpStatus.OK);
	}

}
