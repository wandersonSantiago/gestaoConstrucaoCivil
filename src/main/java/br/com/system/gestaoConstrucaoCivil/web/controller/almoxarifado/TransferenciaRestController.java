package br.com.system.gestaoConstrucaoCivil.web.controller.almoxarifado;

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

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Transferencia;
import br.com.system.gestaoConstrucaoCivil.service.almoxarifado.TransferenciaService;

@RestController
@RequestMapping("/rest/almoxarifado/transferencia")
public class TransferenciaRestController {

	@Autowired
	private TransferenciaService transferenciaService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salva")
	public void salvar(@RequestBody Transferencia transferencia) {
		transferenciaService.salvarAlterar(transferencia);

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista")
	public Collection<Transferencia> buscarTodos() {

		return transferenciaService.buscarTodos();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/recebida/paginacao")
	public Page<Transferencia> recebida(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "0", required = false) int maxResults) {
		return transferenciaService.buscarRecebidaComPaginacao(new PageRequest(page, maxResults));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/enviada/paginacao")
	public Page<Transferencia> enviada(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "0", required = false) int maxResults) {
		return transferenciaService.buscarEnviadaComPaginacao(new PageRequest(page, maxResults));

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscaPorId/{id}")
	public Optional<Transferencia> buscarPorId(@PathVariable Long id) {

		return transferenciaService.buscaPorId(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/recebida")
	public Collection<Transferencia> buscarTransferenciaRecebida() {

		return transferenciaService.buscarTransferenciaRecebida();

	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/enviada")
	public Collection<Transferencia> buscarTransferenciaEnviada() {

		return transferenciaService.buscarTransferenciaEnviada();

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/aceitar")
	public void aceitar(@RequestBody Long numeroNota) {
		transferenciaService.aceitarTransferencia(numeroNota);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/rejeitar")
	public void rejeitar(@RequestBody Long numeroNota) {
		transferenciaService.rejeitarTransferencia(numeroNota);

	}
}
