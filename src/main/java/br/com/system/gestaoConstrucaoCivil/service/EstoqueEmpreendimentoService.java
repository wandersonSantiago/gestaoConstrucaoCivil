package br.com.system.gestaoConstrucaoCivil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.EstoqueEmpreendimento;
import br.com.system.gestaoConstrucaoCivil.repository.EstoqueEmpreendimentoRepository;


@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class EstoqueEmpreendimentoService {

	@Autowired
	private EstoqueEmpreendimentoRepository produtoEstoqueRepository;
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(EstoqueEmpreendimento produtoEstoque)
	{
		produtoEstoqueRepository.save(produtoEstoque);
	}
}
