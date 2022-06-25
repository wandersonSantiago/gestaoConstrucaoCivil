package br.com.app.estoque.api.v1.resource;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.estoque.domain.enuns.UnidadeMedidaEnum;

@RestController
@RequestMapping("/rest/almoxarifado/unidadeMedida")
public class UnidadeMedidaResource {

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista")
	public Collection<UnidadeMedidaEnum> unidadeMedida() {

		return Arrays.asList(UnidadeMedidaEnum.values());
	}
	
}
