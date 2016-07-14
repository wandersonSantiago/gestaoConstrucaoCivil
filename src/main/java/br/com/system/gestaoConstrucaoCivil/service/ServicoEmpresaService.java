package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.ServicoEmpresa;
import br.com.system.gestaoConstrucaoCivil.repository.ServicoEmpresaRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class ServicoEmpresaService {


	@Autowired
	private ServicoEmpresaRepository servicoRepository;
	
	public List<ServicoEmpresa> buscarTodos() {
 		
 		return servicoRepository.findAll();
	}
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(ServicoEmpresa servico)
	{
		servicoRepository.save(servico);
	}
}
