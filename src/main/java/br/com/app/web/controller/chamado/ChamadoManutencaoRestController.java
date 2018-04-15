package br.com.app.web.controller.chamado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.service.chamado.ChamadoManutencaoService;

@RestController
@RequestMapping("/rest/chamado/chamadoManutencao")
public class ChamadoManutencaoRestController {

	@Autowired
	private ChamadoManutencaoService chamadoManutencaoService;

 

}
