package br.com.app.commons.api.v1.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.commons.domain.model.Cargo;
import br.com.app.commons.domain.service.CargoService;

@RestController
@RequestMapping("/rest/recursosHumanos/cargo")
public class CargoResource {

	
	@Autowired
	private CargoService cargoService;

	@Autowired
	private CargoService cargoServices;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cargo> cargos() {

		return cargoService.buscarTodos();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista/paginacao/", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<Cargo> listaComPaginacao(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "0", required = false) int maxResults) {
		return cargoServices.listaComPaginacao(PageRequest.of(page, maxResults));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscaPorId/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Cargo> buscarCargoPorId(@PathVariable Long id) {
		return cargoService.findById(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/salva")
	public void salva(@RequestBody Cargo cargo) {
		
		cargoService.salvarOuEditar(cargo);

	}

	@PutMapping(value = "/altera")
	@ResponseStatus(HttpStatus.CREATED)
	public void updateCargo(@RequestBody Cargo cargo) {
		cargoService.salvarOuEditar(cargo);
	}

}
