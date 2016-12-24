package br.com.system.gestaoConstrucaoCivil.web.controller.almoxarifado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.CotacaoEmpresa;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.EstoqueEmpreendimento;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.EstoqueEmpreendimentoService;

@RestController
@RequestMapping("/rest/produtoEstoque")
public class EstoqueEmpreendimentoRestController {

	@Autowired
	private EstoqueEmpreendimentoService estoqueService;
	
	
	@PutMapping(value = "/alteraProduto")
	public ResponseEntity<CotacaoEmpresa> salvar(@RequestBody EstoqueEmpreendimento estoqueEmpreendimento){
		estoqueService.salvarOuEditar(estoqueEmpreendimento);
		HttpHeaders headers =  new HttpHeaders();
		return new ResponseEntity(headers, HttpStatus.CREATED);				
	}
	@GetMapping(value = "/lista")
	public ResponseEntity<Iterable<EstoqueEmpreendimento>> buscarTodos() {
		return new ResponseEntity<Iterable<EstoqueEmpreendimento>>(estoqueService.buscarTodos(), HttpStatus.OK);
	}
	@GetMapping(value = "/buscaPorCodigo/{codigo}")
	public ResponseEntity<EstoqueEmpreendimento> buscarPorCodigo(@PathVariable String codigo)
	{
	    return new ResponseEntity<EstoqueEmpreendimento>(estoqueService.buscarPorCodigoOuCodigoBarraEstoque(codigo),HttpStatus.OK);
		
	}
}
