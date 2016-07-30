package br.com.system.gestaoConstrucaoCivil.web.controller;

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
import br.com.system.gestaoConstrucaoCivil.entity.EmpresaContratante;

import br.com.system.gestaoConstrucaoCivil.service.DadoEmpresaService;
import br.com.system.gestaoConstrucaoCivil.service.EmpresaContratanteService;
import br.com.system.gestaoConstrucaoCivil.service.EnderecoService;


@RestController
@RequestMapping("rest/adminSistema")
public class EmpresaContratanteRestController {

	@Autowired
	public EmpresaContratanteService empresaContratanteService;
    @Autowired
    public EnderecoService enderecoService;
    @Autowired
    public DadoEmpresaService dadoEmpresaService;
    
	@RequestMapping(method = RequestMethod.GET, value="/listarEmpresa")
	 public ResponseEntity<Iterable<EmpresaContratante>> buscarEmpresaContratante() {	  
	  Iterable<EmpresaContratante> empresaContratante = empresaContratanteService.buscarTodos();
	  return new ResponseEntity<Iterable<EmpresaContratante>>(empresaContratante, HttpStatus.OK);
	 }
	
	@RequestMapping(value = "/listarEmpresaId/{id}", method = RequestMethod.GET)
	public ResponseEntity<EmpresaContratante> buscarEmpresaPorId(@PathVariable Long id) {
		
		return new ResponseEntity<EmpresaContratante>(empresaContratanteService.buscarPorId(id), HttpStatus.OK);
	}
	
	@RequestMapping(value ="/cadastrarEmpresa", method = RequestMethod.POST)
	public ResponseEntity salvarEmpresaContratante(@RequestBody EmpresaContratante empresaContratante, UriComponentsBuilder ucBuilder) {
		
		empresaContratanteService.salvarOuEditar(empresaContratante);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("rest/adminSistema/cadastrarEmpresa/{empresa}").buildAndExpand(empresaContratante.getId()).toUri());
										
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/alterarEmpresa", method = RequestMethod.PUT)
	public ResponseEntity alterarEmpresaContratante(@RequestBody EmpresaContratante empresaContratante, UriComponentsBuilder ucBuilder) {
		
		empresaContratanteService.salvarOuEditar(empresaContratante);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("rest/adminSistema/alterarEmpresa/{empresa}").buildAndExpand(empresaContratante.getId()).toUri());
										
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
}
