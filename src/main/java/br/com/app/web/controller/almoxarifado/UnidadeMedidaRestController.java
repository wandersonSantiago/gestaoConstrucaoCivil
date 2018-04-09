package br.com.app.web.controller.almoxarifado;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.enuns.UnidadeMedidaEnum;

@RestController
@RequestMapping("/rest/almoxarifado/unidadeMedida")
public class UnidadeMedidaRestController {

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista")
	public Collection<UnidadeMedidaEnum> unidadeMedida() {

		return Arrays.asList(UnidadeMedidaEnum.values());
	}
	
}
