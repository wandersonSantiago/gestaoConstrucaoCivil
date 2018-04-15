package br.com.app.web.controller.chamado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.service.chamado.ChamadoTiService;

@RestController
@RequestMapping("/rest/chamado/chamadoTi")
public class ChamadoTiRestController {

	@Autowired
	private ChamadoTiService chamadoTiService;

	 
}
