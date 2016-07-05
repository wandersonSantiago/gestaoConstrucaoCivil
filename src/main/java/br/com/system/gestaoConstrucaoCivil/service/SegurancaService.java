package br.com.system.gestaoConstrucaoCivil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.Seguranca;
import br.com.system.gestaoConstrucaoCivil.repository.SegurancaRepository;


@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class SegurancaService {


	@Autowired
	private SegurancaRepository segurancaRepository;
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(Seguranca seguranca)
	{
		segurancaRepository.save(seguranca);
	}
}
