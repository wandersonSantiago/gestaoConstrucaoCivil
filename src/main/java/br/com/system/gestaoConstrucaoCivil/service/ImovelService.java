package br.com.system.gestaoConstrucaoCivil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.Imovel;
import br.com.system.gestaoConstrucaoCivil.repository.ImovelRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class ImovelService {

	@Autowired
	private ImovelRepository imovelRepository;
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(Imovel imovel)
	{
		imovelRepository.save(imovel);
	}
}
