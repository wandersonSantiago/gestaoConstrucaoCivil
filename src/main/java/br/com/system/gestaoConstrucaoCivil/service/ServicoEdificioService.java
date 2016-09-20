package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.ServicoEdificio;
import br.com.system.gestaoConstrucaoCivil.entity.ServicoEmpresa;
import br.com.system.gestaoConstrucaoCivil.repository.ServicoEdificioRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class ServicoEdificioService  {

	@Autowired
	private ServicoEdificioRepository servicoEdificioRepository;
	
	public List<ServicoEdificio> buscarTodos() {
 		
 		return servicoEdificioRepository.findAll();
	}
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(ServicoEdificio servico)
	{
		servicoEdificioRepository.save(servico);
	}
}
