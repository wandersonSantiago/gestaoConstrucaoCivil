package br.com.system.gestaoConstrucaoCivil.web.controller;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.system.gestaoConstrucaoCivil.enuns.UnidadeMedidaEnum;

@RestController
@RequestMapping("/rest/almoxarifado/unidadeMedida")
public class UnidadeMedidaRestController {

	
	@RequestMapping(method = RequestMethod.GET, value = "/lista")
	public ResponseEntity<Iterable<UnidadeMedidaEnum>> unidadeMedida() {

		Iterable<UnidadeMedidaEnum> unidadeMedida = Arrays.asList(UnidadeMedidaEnum.values());
		return new ResponseEntity<Iterable<UnidadeMedidaEnum>>(unidadeMedida, HttpStatus.OK);
	}
	
}
