package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.entity.PrestadoraServico;
import br.com.system.gestaoConstrucaoCivil.repository.PrestadoraServicoRepository;

@Service
@Transactional
public class PrestadoraServicoService {

	@Autowired
	private PrestadoraServicoRepository prestadoraServicoRepository;
	
	 public List<PrestadoraServico> buscarTodos() {
	 		
	 		return prestadoraServicoRepository.findAll();
	 	}
}
