package br.com.app.web.controller.almoxarifado;

import java.util.Collection;
import java.util.Optional;

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

import br.com.app.entity.almoxarifado.NotaFiscal;
import br.com.app.service.almoxarifado.NotaFiscalService;

@RestController
@RequestMapping("/rest/notaFiscal")
public class NotaFiscalRestController {

	@Autowired
	private NotaFiscalService notaFiscalService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista")
	public Collection<NotaFiscal> buscarTodos() {
		return notaFiscalService.buscarTodos();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscaPorId/{id}")
	public Optional<NotaFiscal> buscarPorId(@PathVariable Long id) {

		return notaFiscalService.buscarPorId(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salva")
	public void salvar(@RequestBody NotaFiscal notaFiscal) {
		notaFiscalService.salvarOuEditar(notaFiscal);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/altera")
	public void alterar(@RequestBody NotaFiscal notaFiscal) {
		notaFiscalService.salvarOuEditar(notaFiscal);
	}
}
