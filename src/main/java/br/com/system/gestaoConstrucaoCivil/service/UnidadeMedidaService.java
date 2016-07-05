package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.entity.UnidadeMedida;
import br.com.system.gestaoConstrucaoCivil.repository.UnidadeMedidaRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class UnidadeMedidaService {


	@Autowired
	private UnidadeMedidaRepository unidadeMedidaRepository;
	
	 public List<UnidadeMedida> buscarTodos() {
	 		 	
	 		return unidadeMedidaRepository.findAll(); 
	 }
	 @Transactional(readOnly = false)
	 public void salvarOuEditar(UnidadeMedida unidadeMedida)
	 {
		 unidadeMedidaRepository.save(unidadeMedida);
	 }
}
