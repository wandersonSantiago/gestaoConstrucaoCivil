package br.com.system.gestaoConstrucaoCivil.web.controller.almoxarifado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.SubCategoriaService;

@RestController
@RequestMapping("/rest/subCategoria")
public class SubCategoriaRestController {

	@Autowired
	 private SubCategoriaService subCategoriaService;
}
