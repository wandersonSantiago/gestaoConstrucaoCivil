package br.com.system.gestaoConstrucaoCivil.web.controller;

import java.util.List;

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

import br.com.system.gestaoConstrucaoCivil.entity.Cargo;
import br.com.system.gestaoConstrucaoCivil.service.CargoService;
import br.com.system.gestaoConstrucaoCivil.service.interfaceservice.ICargoService;

@RestController
@RequestMapping("/rest/recursosHumanos/cargo")
public class CargoRestController {

	@Autowired
	private ICargoService<Cargo> cargoService;

	@Autowired
	private CargoService cargoServiceNew;

	@Autowired
	private CargoService cargoServices;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cargo> cargos() {

		return cargoServiceNew.buscarTodos();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/lista/paginacao/", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<Cargo> listaComPaginacao(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "0", required = false) int maxResults) {
		return cargoServices.listaComPaginacao(new PageRequest(page, maxResults));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/buscaPorId/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Cargo buscarCargoPorId(@PathVariable Long id) {
		return cargoService.buscarPorId(id);
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
