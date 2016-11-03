package br.com.system.gestaoConstrucaoCivil.web.controller.almoxarifado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoCasa;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.RequisicaoCasaService;

@RestController
@RequestMapping("/rest/almoxarifado/estoque/requisicaoCasa")
public class RequisicaoCasaRestController {

	@Autowired
	private RequisicaoCasaService requisicaoCasaService;
	
	@RequestMapping(value = "/salva", method = RequestMethod.POST)
	public ResponseEntity salvar(@RequestBody List<RequisicaoCasa> requisicaoCasa,UriComponentsBuilder ucBuilder)
	{ 
		requisicaoCasaService.salvarOuEditar(requisicaoCasa);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
}
