package br.com.system.gestaoConstrucaoCivil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.EstoqueEmpreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.NotaFiscalProduto;
import br.com.system.gestaoConstrucaoCivil.repository.EstoqueEmpreendimentoRepository;


@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class EstoqueEmpreendimentoService {

	@Autowired
	private EstoqueEmpreendimentoRepository estoqueRepository;
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(EstoqueEmpreendimento produtoEstoque)
	{
		estoqueRepository.save(produtoEstoque);
	}
	
	@Transactional(readOnly = false)
	public void entradaEstoque(NotaFiscalProduto notaProduto)
	{
		for(int i = 0  ; i < notaProduto.getItens().size(); i++)
		{
			
			if(existeProduto(notaProduto.getItens().get(i).getProduto().getId()))
			{
			
			 adicionarQuantidadeEstoque(notaProduto.getItens().get(i).getQuantidade(), notaProduto.getItens().get(i).getProduto().getId());
			}else{
				
	           EstoqueEmpreendimento estoque = new EstoqueEmpreendimento();
	           estoque.adicionarProduto(notaProduto.getItens().get(i).getProduto());
	           estoque.setQuantidade(notaProduto.getItens().get(i).getQuantidade());
		    }
		}
			
	}
	@Transactional(readOnly = false)
	public void adicionarQuantidadeEstoque(Integer quantidade , Long idProduto)
	{
		estoqueRepository.adicionarQuantidadeEstoque(quantidade, idProduto);
	}
	
	@Transactional
	public void baixarEstoque(Integer quantidade,Long idProduto)
	{
		estoqueRepository.baixarEstoque(quantidade,idProduto);
	}
	
	public boolean existeProduto(Long id)
	{
	  return estoqueRepository.existeProduto(id);
	}
	  
}
