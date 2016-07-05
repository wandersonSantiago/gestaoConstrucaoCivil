package br.com.system.gestaoConstrucaoCivil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.ProdutoEstoque;
import br.com.system.gestaoConstrucaoCivil.repository.ProdutoEstoqueRepository;


@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class ProdutoEstoqueService {

	@Autowired
	private ProdutoEstoqueRepository produtoEstoqueRepository;
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(ProdutoEstoque produtoEstoque)
	{
		produtoEstoqueRepository.save(produtoEstoque);
	}
}
