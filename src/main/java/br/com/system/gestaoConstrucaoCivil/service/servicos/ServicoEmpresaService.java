package br.com.system.gestaoConstrucaoCivil.service.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.servicos.ServicoEmpresa;
import br.com.system.gestaoConstrucaoCivil.repository.servicos.ServicoEmpresaRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class ServicoEmpresaService {


	@Autowired
	private ServicoEmpresaRepository servicoRepository;
	
	public Page<ServicoEmpresa> buscarTodos(PageRequest pages) {
 		
 		return servicoRepository.findAll(pages);
	}
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(ServicoEmpresa servico)
	{
		servicoRepository.save(servico);
	}

	public Iterable<ServicoEmpresa> lista() {
		return servicoRepository.findAll();
	}
}
