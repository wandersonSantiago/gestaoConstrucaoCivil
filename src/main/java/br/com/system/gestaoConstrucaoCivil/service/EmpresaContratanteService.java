package br.com.system.gestaoConstrucaoCivil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.EmpresaContratante;
import br.com.system.gestaoConstrucaoCivil.repository.EmpresaContratanteRepository;


@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class EmpresaContratanteService {

	@Autowired
	private EmpresaContratanteRepository empresaContratanteRepository;
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(EmpresaContratante empresaContratante)
	{
		empresaContratanteRepository.save(empresaContratante);
	}
	
	public EmpresaContratante buscarPorId(Long id)
	{
		return empresaContratanteRepository.findOne(id);
	}
	
	
}
