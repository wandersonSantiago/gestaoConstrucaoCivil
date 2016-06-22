package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.entity.UnidadeMedida;
import br.com.system.gestaoConstrucaoCivil.repository.UnidadeMedidaRepository;

@Service
@Transactional
public class UnidadeMedidaService {


	@Autowired
	private UnidadeMedidaRepository unidadeMedidaRepository;
	
	 public List<UnidadeMedida> buscarTodos() {
	 		 	
	 		return unidadeMedidaRepository.findAll(); 
	 }
}
