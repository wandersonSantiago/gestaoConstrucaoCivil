package br.com.system.gestaoConstrucaoCivil.web.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.gestaoConstrucaoCivil.entity.ConfigEmpreendimento;
import br.com.system.gestaoConstrucaoCivil.service.ConfigEmpreendimentoService;
 
@RestController
@RequestMapping("/rest/empreendimento/configuracao")
public class ConfigEmpreendimentoRestController {
     
    @Autowired
    private ConfigEmpreendimentoService configEmpreeendimentoService;
     
    @RequestMapping(method = RequestMethod.GET, value = "/lista")
    public ResponseEntity<Iterable<ConfigEmpreendimento>> buscarTodos() {
 
        Iterable<ConfigEmpreendimento> configEmpreendimento = configEmpreeendimentoService.buscarTodos();
        return new ResponseEntity<Iterable<ConfigEmpreendimento>>(configEmpreendimento, HttpStatus.OK);
    }
     
    @RequestMapping(value = "/buscaPorId/{id}", method = RequestMethod.GET)
    public ResponseEntity<ConfigEmpreendimento> buscarPorId(@PathVariable Long id) {
        return new ResponseEntity<ConfigEmpreendimento>(configEmpreeendimentoService.buscarPorId(id), HttpStatus.OK);
    }
     
     
}