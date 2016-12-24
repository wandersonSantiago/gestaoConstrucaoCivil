package br.com.system.gestaoConstrucaoCivil.web.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.gestaoConstrucaoCivil.entity.ConfigEmpreendimento;
import br.com.system.gestaoConstrucaoCivil.service.ConfigEmpreendimentoService;
 
@RestController
@RequestMapping("/rest/empreendimento/configuracao")
public class ConfigEmpreendimentoRestController {
     
    @Autowired
    private ConfigEmpreendimentoService configEmpreeendimentoService;
     
    @GetMapping(value = "/lista")
    public ResponseEntity<Iterable<ConfigEmpreendimento>> buscarTodos() {
    	return new ResponseEntity<Iterable<ConfigEmpreendimento>>(configEmpreeendimentoService.buscarTodos(), HttpStatus.OK);
    }
     
    @GetMapping(value = "/buscaPorId/{id}")
    public ResponseEntity<ConfigEmpreendimento> buscarPorId(@PathVariable Long id) {
        return new ResponseEntity<ConfigEmpreendimento>(configEmpreeendimentoService.buscarPorId(id), HttpStatus.OK);
    }
     
     
}