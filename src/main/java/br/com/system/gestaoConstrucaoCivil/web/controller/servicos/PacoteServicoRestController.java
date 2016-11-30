package br.com.system.gestaoConstrucaoCivil.web.controller.servicos;

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

import br.com.system.gestaoConstrucaoCivil.entity.servicos.PacoteServico;
import br.com.system.gestaoConstrucaoCivil.service.servicos.PacoteServicoService;

@RestController
@RequestMapping("/rest/servicos/pacotes")
public class PacoteServicoRestController {

	@Autowired
	 private PacoteServicoService pacoteServicoService;
	
	 @RequestMapping(method = RequestMethod.GET, value="/lista")
	 public ResponseEntity<Iterable<PacoteServico>> buscarTodos() {	  
	  
	  Iterable<PacoteServico> pacoteServico = pacoteServicoService.buscarTodos();
	  return new ResponseEntity<Iterable<PacoteServico>>(pacoteServico, HttpStatus.OK);
	 }
	 
	 @RequestMapping(value="/salva", method = RequestMethod.POST)
	 public ResponseEntity salvar(@RequestBody PacoteServico pacoteServico,UriComponentsBuilder ucBuilder)
	 {	 pacoteServicoService.salvarOuEditar(pacoteServico);
		 HttpHeaders headers =new HttpHeaders();
		 headers.setLocation(ucBuilder.path("/rest/servicos/pacotes/salva/{id}").buildAndExpand(pacoteServico.getId()).toUri());
		 return new ResponseEntity(headers, HttpStatus.CREATED);
	 }
	 
	 @RequestMapping(value="/altera", method = RequestMethod.PUT)
	 public ResponseEntity alterar(@RequestBody PacoteServico pacoteServico,UriComponentsBuilder ucBuilder)
	 {	 pacoteServicoService.salvarOuEditar(pacoteServico);
		 HttpHeaders headers =new HttpHeaders();
		 headers.setLocation(ucBuilder.path("/rest/servicos/pacotes/altera/{id}").buildAndExpand(pacoteServico.getId()).toUri());
		 return new ResponseEntity(headers, HttpStatus.CREATED);
	 }
	 
	 @RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
		public ResponseEntity<PacoteServico> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<PacoteServico>(pacoteServicoService.buscarPorId(id), HttpStatus.OK);
		}
	 
	
}
