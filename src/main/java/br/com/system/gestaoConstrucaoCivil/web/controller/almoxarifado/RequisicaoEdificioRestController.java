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
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoEdificio;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.RequisicaoEdificioService;


@RestController
@RequestMapping("/rest/almoxarifado/estoque/requisicaoEdificio")
public class RequisicaoEdificioRestController {

	@Autowired
	private RequisicaoEdificioService requisicaoEdificioService;
	
	@RequestMapping(value = "/salva", method = RequestMethod.POST)
	public ResponseEntity salvar(@RequestBody RequisicaoEdificio requisicaoEdificio,UriComponentsBuilder ucBuilder)
	{ 
		requisicaoEdificioService.salvarOuEditar(requisicaoEdificio);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
}
