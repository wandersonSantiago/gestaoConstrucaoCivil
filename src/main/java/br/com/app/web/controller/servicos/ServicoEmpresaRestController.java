package br.com.app.web.controller.servicos;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.entity.servicos.ServicoEmpresa;
import br.com.app.service.servicos.ServicoEmpresaService;

@RestController
@RequestMapping("/rest/servico/vincular")
public class ServicoEmpresaRestController {

	@Autowired
	private ServicoEmpresaService servicoService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/prestadora/{id}/pagamentos/liberado")
	public Collection<ServicoEmpresa> buscarServicosPagamentoLiberadoDaPrestadora(@PathVariable Long id) {
		return servicoService.buscarServicosPagamentoLiberadoDaPrestadora(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void insert(@RequestBody ServicoEmpresa servico) {
		servicoService.insert(servico);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/prestadora/{id}/pagamentos/efetuar")
	public void efetuarPagamento(@PathVariable Long id) {
		servicoService.efetuarPagamento(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/prestadora/{id}/pagamentos")
	public Collection<ServicoEmpresa> buscarServicosDaPrestadora(@PathVariable Long id) {
		return servicoService.buscarServicosDaPrestadora(id);
		 
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscarPorId/{id}")
	public ServicoEmpresa findById(@PathVariable Long id) {
		return servicoService.findById(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/vistoria")
	public void alteraVistoria(@RequestBody ServicoEmpresa servico) {
		servicoService.salvarOuEditarVistoria(servico);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/pagamento/salvar")
	public void salvarPagamento(@RequestBody ServicoEmpresa servico) {

		servicoService.salvarPagamento(servico);
	}

}
