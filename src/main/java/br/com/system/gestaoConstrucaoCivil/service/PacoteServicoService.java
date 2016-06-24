package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.entity.PacoteServico;
import br.com.system.gestaoConstrucaoCivil.repository.PacoteServicoRepository;

@Service
@Transactional
public class PacoteServicoService {

	@Autowired
	private PacoteServicoRepository pacoteServicoRepository;
	
	 public List<PacoteServico> buscarTodos() {
			
			return pacoteServicoRepository.findAll();
		}
	 
}
