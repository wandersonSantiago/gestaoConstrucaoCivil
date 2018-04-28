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

import br.com.app.entity.almoxarifado.Fabricante;
import br.com.app.service.almoxarifado.FabricanteService;

@RestController
@RequestMapping("/rest/almoxarifado/fabricante")
public class FabricanteRestController {

	@Autowired
	private FabricanteService fabricanteService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista")
	public Collection<Fabricante> buscarTodos() {
		return fabricanteService.buscarTodos();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscaPorId/{id}")
	public Optional<Fabricante> buscarPorId(@PathVariable Long id) {

		return fabricanteService.buscarPorId(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salva")
	public void salvar(@RequestBody Fabricante fabricante) {
		fabricanteService.salvarOuEditar(fabricante);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping(value = "/altera")
	public void alterar(@RequestBody Fabricante fabricante) {
		fabricanteService.salvarOuEditar(fabricante);

	}
}
