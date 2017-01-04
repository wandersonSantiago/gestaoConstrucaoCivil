package br.com.system.gestaoConstrucaoCivil.service.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.servicos.ServicoOutros;
import br.com.system.gestaoConstrucaoCivil.repository.servicos.ServicoOutrosRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class ServicoOutrosService {

	@Autowired
	private ServicoOutrosRepository servicoOutrosRepository;
	
	
	public List<ServicoOutros> buscarTodos() {
		
		return servicoOutrosRepository.findAll();
	}
	@Transactional(readOnly = false)
	public void  salvarOuEditar(ServicoOutros servico)
	{
		servicoOutrosRepository.save(servico);
	}
	public ServicoOutros buscarPorId(Long id) {
		return servicoOutrosRepository.findOne(id);
	}
}
