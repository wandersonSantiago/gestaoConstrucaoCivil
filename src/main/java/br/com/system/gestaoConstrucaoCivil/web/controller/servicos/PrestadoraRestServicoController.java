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

import br.com.system.gestaoConstrucaoCivil.entity.Cargo;
import br.com.system.gestaoConstrucaoCivil.entity.EmpresaContratante;
import br.com.system.gestaoConstrucaoCivil.entity.servicos.PrestadoraServico;
import br.com.system.gestaoConstrucaoCivil.service.servicos.PrestadoraServicoService;

@RestController
@RequestMapping("/rest/servicos/prestadoraServico")
public class PrestadoraRestServicoController {
	 
	 @Autowired
	 private PrestadoraServicoService prestadoraServicoService;
	
	 @RequestMapping(method = RequestMethod.GET, value="/lista")
	 public ResponseEntity<Iterable<PrestadoraServico>> lista() {	  
	  
	  Iterable<PrestadoraServico> prestadoraServico = prestadoraServicoService.buscarTodos();
	  return new ResponseEntity<Iterable<PrestadoraServico>>(prestadoraServico, HttpStatus.OK);
	 }
	 
	 
	 @RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
	 public ResponseEntity<PrestadoraServico> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<PrestadoraServico>(prestadoraServicoService.buscarPorId(id), HttpStatus.OK);
		}

	 
	 
	 @RequestMapping(value="/salva", method = RequestMethod.POST)
	 public ResponseEntity salva(@RequestBody PrestadoraServico prestadoraServico,UriComponentsBuilder ucBuilder)
	 {
		 System.out.println(prestadoraServico);
		 
		 prestadoraServicoService.salvarOuEditar(prestadoraServico);
		 HttpHeaders headers =new HttpHeaders();
		 headers.setLocation(ucBuilder.path("/rest/prestadoraServico/salva/{prestadoraServico}").buildAndExpand(prestadoraServico.getId()).toUri());
		 return new ResponseEntity(headers, HttpStatus.CREATED);
	 }
	 
		@RequestMapping(value = "/altera", method = RequestMethod.PUT)
		public ResponseEntity altera(@RequestBody PrestadoraServico prestadoraServico, UriComponentsBuilder ucBuilder) {
			
			prestadoraServicoService.salvarOuEditar(prestadoraServico);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/rest/prestadoraServico/altera/{prestadoraServico}").buildAndExpand(prestadoraServico.getId()).toUri());
											
			return new ResponseEntity(headers, HttpStatus.CREATED);
		}
}
