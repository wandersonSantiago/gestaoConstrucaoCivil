package br.com.system.gestaoConstrucaoCivil.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.gestaoConstrucaoCivil.service.TipoEmpreendimentoService;

@RestController
@RequestMapping("/rest/tipoEmpreendimento")
public class TipoEmpreendimentoRestController {

	@Autowired
	 private TipoEmpreendimentoService tipoEmpreendimentoService;
}
