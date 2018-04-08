package br.com.system.gestaoConstrucaoCivil.web.controller.servicos;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.gestaoConstrucaoCivil.entity.servicos.ServicoEdificio;
import br.com.system.gestaoConstrucaoCivil.entity.servicos.ServicoEmpresa;
import br.com.system.gestaoConstrucaoCivil.service.servicos.ServicoEmpresaService;

@RestController
@RequestMapping("/rest/servico/vincular")
public class ServicoEmpresaRestController {

	@Autowired
	private ServicoEmpresaService servicoService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista")
	public Collection<ServicoEmpresa> buscarTodos() {
		return servicoService.lista();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/prestadora/{id}/pagamentos")
	public  Collection<ServicoEmpresa> buscarServicosDaPrestadora(@PathVariable Long id) {
		return servicoService.buscarServicosDaPrestadora(id);
			 
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/prestadora/{id}/pagamentos/liberado")
	public Collection<ServicoEmpresa> buscarServicosPagamentoLiberadoDaPrestadora(@PathVariable Long id) {
		return  servicoService.buscarServicosPagamentoLiberadoDaPrestadora(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public  Page<ServicoEmpresa>  lista(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "0", required = false) int maxResults) {
		return servicoService.buscarTodos(new PageRequest(page, maxResults));
		 
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salvar")
	public void salvar(@RequestBody ServicoEmpresa servico) {
		servicoService.salvarOuEditar(servico);
	
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/prestadora/{id}/pagamentos/efetuar")
	public void efetuarPagamento(@PathVariable Long id) {
		servicoService.efetuarPagamento(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscarPorId/{id}")
	public Optional<ServicoEmpresa>  buscarPorId(@PathVariable Long id) {
		return  servicoService.buscarPorId(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/pagamento/edificio/salvar")
	public void salvarPagamentoEdificio(@RequestBody ServicoEdificio servico) {

		servicoService.salvarPagamento(servico);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/pagamento/casa/salvar")
	public void salvarPagamentoCasa(@RequestBody ServicoEdificio servico) {

		servicoService.salvarPagamento(servico);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/pagamento/outros/salvar")
	public void salvarPagamentoOutros(@RequestBody ServicoEdificio servico) {
		servicoService.salvarPagamento(servico);

	}

}
