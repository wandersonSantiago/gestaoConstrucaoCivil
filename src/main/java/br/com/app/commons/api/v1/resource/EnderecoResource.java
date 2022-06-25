package br.com.app.commons.api.v1.resource;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.commons.domain.enuns.UfEnum;

@RestController
@RequestMapping("/rest/endereco")
public class EnderecoResource {


	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/uf")
	public Collection<UfEnum> uf() {

		return  Arrays.asList(UfEnum.values());
	}
}
