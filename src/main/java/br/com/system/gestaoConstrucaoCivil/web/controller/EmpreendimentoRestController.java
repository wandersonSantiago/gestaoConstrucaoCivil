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

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.EmpresaContratante;
import br.com.system.gestaoConstrucaoCivil.entity.TipoEmpreendimento;
import br.com.system.gestaoConstrucaoCivil.service.EmpreendimentoService;
import br.com.system.gestaoConstrucaoCivil.service.EnderecoService;

@RestController
@RequestMapping("rest/empreendimento/cadastrarEmpreendimento")
public class EmpreendimentoRestController {


	 @Autowired
	 private EmpreendimentoService empreendimentoService;
	 @Autowired
	 private EnderecoService enderecoService;
	 
	 @RequestMapping(method = RequestMethod.GET, value="/listaEmpreendimento")
	 public ResponseEntity<Iterable<Empreendimento>> buscarEmpreendimento() {	  
	  System.out.println("lista ok");
	  Iterable<Empreendimento> empreendimento = empreendimentoService.buscarTodos();
	  return new ResponseEntity<Iterable<Empreendimento>>(empreendimento, HttpStatus.OK);
	 }
	 

		@RequestMapping(method = RequestMethod.POST)
		public ResponseEntity salvarEmpresaContratante(@RequestBody Empreendimento empreendimento, UriComponentsBuilder ucBuilder) {
		
			if(empreendimento.getEnderecoEmpreendimento() == null)
			{
			  System.out.println("SIM"); 
			}
			else
			{
			 System.out.println("NAO");
			}
			enderecoService.salvarOuEditar(empreendimento.getEnderecoEmpreendimento());
			System.out.println("Chamadou o salvar");
			empreendimentoService.salvarOuEditar(empreendimento);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("rest/empreendimento/cadastrarEmpreendimento/{empreendimento}").buildAndExpand(empreendimento.getId()).toUri());
											
			return new ResponseEntity(headers, HttpStatus.CREATED);
		}

}
