package br.com.system.gestaoConstrucaoCivil.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.gestaoConstrucaoCivil.entity.EmpresaContratante;
import br.com.system.gestaoConstrucaoCivil.service.EmpresaContratanteService;

@RestController
@RequestMapping("/rest/empresaContratante") 
public class EmpresaContratanteRestController {

	@Autowired
	public EmpresaContratanteService empresaContratanteService;
	
	 @RequestMapping(value="/{id}",method = RequestMethod.GET)
	 public ResponseEntity<EmpresaContratante> buscarCargo(@PathVariable Long id ){
	  return  new ResponseEntity<EmpresaContratante>(empresaContratanteService.buscarPorId(id), HttpStatus.OK); 
	 }


}
