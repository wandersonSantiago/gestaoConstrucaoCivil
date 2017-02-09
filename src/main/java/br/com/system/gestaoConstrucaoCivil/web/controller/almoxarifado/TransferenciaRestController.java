package br.com.system.gestaoConstrucaoCivil.web.controller.almoxarifado;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Transferencia;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.TransferenciaService;

@RestController
@RequestMapping("/rest/almoxarifado/transferencia")
public class TransferenciaRestController {
	
	@Autowired
	private TransferenciaService transferenciaService;
	
	@PostMapping(value = "/salva")
	public ResponseEntity<Collection<Transferencia>> salvar(@RequestBody Transferencia transferencia){
		transferenciaService.salvarAlterar(transferencia);
		HttpHeaders headers =  new HttpHeaders();
		return new ResponseEntity<>(headers, HttpStatus.CREATED);				
	}
	
	@GetMapping(value = "/lista")
	public ResponseEntity<Collection<Transferencia>> buscarTodos(){
		
		return new ResponseEntity<Collection<Transferencia>>(transferenciaService.buscarTodos(), HttpStatus.OK);
	}
	@GetMapping(value = "/recebida/paginacao")
	public ResponseEntity<Page<Transferencia>> recebida(@RequestParam(defaultValue="0", required=false) int page
			,@RequestParam(defaultValue="0", required=false) int maxResults) {
		Page<Transferencia> objeto = transferenciaService.buscarRecebidaComPaginacao(new PageRequest(page, maxResults));
		return new ResponseEntity<Page<Transferencia>>(objeto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/enviada/paginacao")
	public ResponseEntity<Page<Transferencia>> enviada(@RequestParam(defaultValue="0", required=false) int page
			,@RequestParam(defaultValue="0", required=false) int maxResults) {
		Page<Transferencia> objeto = transferenciaService.buscarEnviadaComPaginacao(new PageRequest(page, maxResults));
		return new ResponseEntity<Page<Transferencia>>(objeto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/buscaPorId/{id}")
	public ResponseEntity<Transferencia> buscarPorId(@PathVariable Long id) {

		return new ResponseEntity<Transferencia>(transferenciaService.buscaPorId(id), HttpStatus.OK);
	}

	@GetMapping(value = "/recebida")
	public ResponseEntity<Collection<Transferencia>> buscarTransferenciaRecebida() {
		
    	return new ResponseEntity<Collection<Transferencia>>(transferenciaService.buscarTransferenciaRecebida(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/enviada")
	public ResponseEntity<Collection<Transferencia>>  buscarTransferenciaEnviada() {
		
		return new ResponseEntity<Collection<Transferencia>>(transferenciaService.buscarTransferenciaEnviada(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/aceitar")
	public ResponseEntity<Collection<Transferencia>> aceitar(@RequestBody Long numeroNota){
		transferenciaService.aceitarTransferencia(numeroNota);
		return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);				
	}
	
	@PostMapping(value = "/rejeitar")
	public ResponseEntity<Collection<Transferencia>> rejeitar(@RequestBody Long numeroNota){
		transferenciaService.rejeitarTransferencia(numeroNota);
		return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);				
	}
}
