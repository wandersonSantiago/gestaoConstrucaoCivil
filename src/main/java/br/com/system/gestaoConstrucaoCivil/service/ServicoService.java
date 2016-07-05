package br.com.system.gestaoConstrucaoCivil.service;

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
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(Servico servico)
	{
		servicoRepository.save(servico);
	}
}
