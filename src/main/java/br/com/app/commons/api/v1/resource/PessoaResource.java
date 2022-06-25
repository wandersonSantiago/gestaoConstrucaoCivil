package br.com.app.commons.api.v1.resource;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.commons.domain.enuns.EstadoCivilEnum;
import br.com.app.commons.domain.service.PessoaService;

@RestController
@RequestMapping("/rest/pessoa")
public class PessoaResource {

	@Autowired
	private PessoaService pessoaService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/existeCpf/{cpf}")
	public Boolean existeCpf(@PathVariable String cpf) {

		return pessoaService.existeCpf(cpf);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/existeRg/{rg}")
	public Boolean existeRg(@PathVariable String rg) {

		return pessoaService.existeRg(rg);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/estadoCivil")
	public Collection<EstadoCivilEnum> estadoCivil() {

		return Arrays.asList(EstadoCivilEnum.values());
	}
}
