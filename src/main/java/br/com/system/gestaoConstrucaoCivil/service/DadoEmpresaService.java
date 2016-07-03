package br.com.system.gestaoConstrucaoCivil.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.DadoEmpresa;
import br.com.system.gestaoConstrucaoCivil.repository.DadoEmpresaRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class DadoEmpresaService {

	@Autowired
	private DadoEmpresaRepository dadoEmpresaRepository;
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(DadoEmpresa dadoEmpresa)
	{
		dadoEmpresaRepository.save(dadoEmpresa);
	}
	
}
