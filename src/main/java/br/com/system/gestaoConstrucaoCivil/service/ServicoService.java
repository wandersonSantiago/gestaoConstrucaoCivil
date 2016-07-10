package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.Servico;
import br.com.system.gestaoConstrucaoCivil.repository.ServicoRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class ServicoService {


	@Autowired
	private ServicoRepository servicoRepository;
	
	public List<Servico> buscarTodos() {
 		
 		return servicoRepository.findAll();
	}
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(Servico servico)
	{
		servicoRepository.save(servico);
	}
}
