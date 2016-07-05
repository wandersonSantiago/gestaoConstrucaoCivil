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
@RequestMapping("rest/adminSistema/cadastrarEmpresa")
public class EmpresaContratanteRestController {

	@Autowired
	public EmpresaContratanteService empresaContratanteService;
    @Autowired
    public EnderecoService enderecoService;
    @Autowired
    public DadoEmpresaService dadoEmpresaService;
    
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<EmpresaContratante> buscarCargo(@PathVariable Long id) {
		System.out.println("Chamadou o lista");
		return new ResponseEntity<EmpresaContratante>(empresaContratanteService.buscarPorId(id), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity salvarEmpresaContratante(@RequestBody EmpresaContratante empresaContratante, UriComponentsBuilder ucBuilder) {
		System.out.println("Chamadou o salvar");
		enderecoService.salvarOuEditar(empresaContratante.getDadoEmpresa().getEndereco());
	    dadoEmpresaService.salvarOuEditar(empresaContratante.getDadoEmpresa());
		empresaContratanteService.salvarOuEditar(empresaContratante);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("rest/adminSistema/cadastrarEmpresa/{empresa}").buildAndExpand(empresaContratante.getId()).toUri());
										
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

}
