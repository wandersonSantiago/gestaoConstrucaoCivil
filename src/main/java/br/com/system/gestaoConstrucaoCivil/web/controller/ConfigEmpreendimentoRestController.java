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
 
import br.com.system.gestaoConstrucaoCivil.entity.ConfigEmpreendimento;
import br.com.system.gestaoConstrucaoCivil.service.ConfigEmpreendimentoService;
 
@RestController
@RequestMapping("/rest/servico")
public class ConfigEmpreendimentoRestController {
     
    @Autowired
    ConfigEmpreendimentoService configEmpreeendimentoService;
     
     
    @RequestMapping(value = "/cadastrarConfigEmpreendimento", method = RequestMethod.POST)
    public ResponseEntity salvarConfigEmpreendimento(@RequestBody ConfigEmpreendimento configEmpreendimento, UriComponentsBuilder ucBuilder) {
        configEmpreeendimentoService.salvarOuEditar(configEmpreendimento);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("rest/servico/cadastrarConfigEmpreendimento/{configEmpreendimento}").buildAndExpand(configEmpreendimento.getId()).toUri());
       System.out.println(configEmpreendimento);
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }
     
    @RequestMapping(method = RequestMethod.GET, value = "/listarConfigEmpreendimento")
    public ResponseEntity<Iterable<ConfigEmpreendimento>> buscarConfigEmpreendimento() {
 
        Iterable<ConfigEmpreendimento> configEmpreendimento = configEmpreeendimentoService.buscarTodos();
        return new ResponseEntity<Iterable<ConfigEmpreendimento>>(configEmpreendimento, HttpStatus.OK);
    }
     
    @RequestMapping(value = "/listarConfiEmpreendimento/{id}", method = RequestMethod.GET)
    public ResponseEntity<ConfigEmpreendimento> buscarConfigEmpreendimentoPorId(@PathVariable Long id) {
        return new ResponseEntity<ConfigEmpreendimento>(configEmpreeendimentoService.buscarPorId(id), HttpStatus.OK);
    }
     
     
}