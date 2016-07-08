package br.com.system.gestaoConstrucaoCivil.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.Cargo;
import br.com.system.gestaoConstrucaoCivil.entity.EmpresaContratante;
import br.com.system.gestaoConstrucaoCivil.entity.TipoEmpreendimento;
import br.com.system.gestaoConstrucaoCivil.service.TipoEmpreendimentoService;

@RestController
@RequestMapping("rest/tipoEmpreendimento")
public class TipoEmpreendimentoRestController {

	@Autowired
	 private TipoEmpreendimentoService tipoEmpreendimentoService;
	
	@RequestMapping(method = RequestMethod.GET, value="/listarTipoEmpreendimento")
	 public ResponseEntity<Iterable<TipoEmpreendimento>> buscarTipoEmpreendimento() {	  
	  System.out.println("lista ok");
	  Iterable<TipoEmpreendimento> tipoEmpreendimento = tipoEmpreendimentoService.buscarTodos();
	  return new ResponseEntity<Iterable<TipoEmpreendimento>>(tipoEmpreendimento, HttpStatus.OK);
	 }
	
	
	@RequestMapping(method = RequestMethod.POST, value="/cadastrarTipoEmpreendimento")
	public ResponseEntity salvarTipoEmpreendimento(@RequestBody TipoEmpreendimento tipoEmpreendimento, UriComponentsBuilder ucBuilder) {
		System.out.println("Chamadou o salvar");
		tipoEmpreendimentoService.salvarOuEditar(tipoEmpreendimento);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("rest/tipoEmpreendimento/cadastrarTipoEmpreendimento/{tipoEmpreendimento}").buildAndExpand(tipoEmpreendimento.getId()).toUri());
										
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

}
