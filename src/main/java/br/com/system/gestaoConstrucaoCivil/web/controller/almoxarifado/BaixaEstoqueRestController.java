package br.com.system.gestaoConstrucaoCivil.web.controller.almoxarifado;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.BaixaEstoque;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.BaixaEstoqueService;

@RestController
@RequestMapping("/rest/almoxarifado/estoque/baixa")
public class BaixaEstoqueRestController {

	@Autowired
	private BaixaEstoqueService baixaEstoqueService;
	
	@RequestMapping(value = "/salva", method = RequestMethod.POST)
	public ResponseEntity salvar(@RequestBody List<BaixaEstoque> baixaEstoque,UriComponentsBuilder ucBuilder)
	{ 
		baixaEstoqueService.salvarOuEditar(baixaEstoque);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}
	@RequestMapping(method = RequestMethod.GET, value = "/lista")
	public ResponseEntity<Iterable<BaixaEstoque>> buscarTodos()
	{
		Iterable<BaixaEstoque> baixaEstoque = baixaEstoqueService.buscarTodos(); 
		return new ResponseEntity<Iterable<BaixaEstoque>>(baixaEstoque, HttpStatus.OK);
	}
}
