package br.com.system.gestaoConstrucaoCivil.web.controller.almoxarifado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.EstoqueEmpreendimento;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.EstoqueEmpreendimentoService;

@RestController
@RequestMapping("/rest/produtoEstoque")
public class EstoqueEmpreendimentoRestController {

	@Autowired
	private EstoqueEmpreendimentoService estoqueService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/lista")
	public ResponseEntity<Iterable<EstoqueEmpreendimento>> buscarTodos() {
		Iterable<EstoqueEmpreendimento> estoqueEmpreendimento = estoqueService.buscarTodos();
		return new ResponseEntity<Iterable<EstoqueEmpreendimento>>(estoqueEmpreendimento, HttpStatus.OK);
	}
}
