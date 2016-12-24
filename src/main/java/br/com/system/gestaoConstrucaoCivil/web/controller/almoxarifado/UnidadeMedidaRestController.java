package br.com.system.gestaoConstrucaoCivil.web.controller.almoxarifado;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.gestaoConstrucaoCivil.enuns.UnidadeMedidaEnum;

@RestController
@RequestMapping("/rest/almoxarifado/unidadeMedida")
public class UnidadeMedidaRestController {

	
	@GetMapping(value = "/lista")
	public ResponseEntity<Iterable<UnidadeMedidaEnum>> unidadeMedida() {

		return new ResponseEntity<Iterable<UnidadeMedidaEnum>>(Arrays.asList(UnidadeMedidaEnum.values()), HttpStatus.OK);
	}
	
}
