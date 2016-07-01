package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.entity.Cargo;
import br.com.system.gestaoConstrucaoCivil.repository.CargoRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class CargoService {

	@Autowired
	private CargoRepository cargoRepository;
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(Cargo cargo)
	{
		cargoRepository.save(cargo);
	}
	
    public List<Cargo> buscarTodos(){
		
		return cargoRepository.findAll();
	}
}
