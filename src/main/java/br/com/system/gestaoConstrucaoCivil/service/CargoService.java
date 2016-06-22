package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.entity.Cargo;
import br.com.system.gestaoConstrucaoCivil.repository.CargoRepository;

@Service
@Transactional
public class CargoService {

	@Autowired
	private CargoRepository cargoRepository;
	
    public List<Cargo> buscarTodos() {
		
		return cargoRepository.findAll();
	}
}
