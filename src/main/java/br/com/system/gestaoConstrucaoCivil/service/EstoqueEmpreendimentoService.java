package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.EstoqueEmpreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.NotaFiscalProduto;
import br.com.system.gestaoConstrucaoCivil.repository.EstoqueEmpreendimentoRepository;


@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class EstoqueEmpreendimentoService {

	@Autowired
	private EstoqueEmpreendimentoRepository estoqueRepository;
	@Autowired
	private EmpreendimentoService empreendimento;
	
	
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
			  EstoqueEmpreendimento estoque = estoqueRepository.estoque(31L, notaProduto.getItens().get(i).getProduto().getId());
			  Integer qt = estoque.getQuantidade();
			  qt += notaProduto.getItens().get(i).getQuantidade();
			  estoque.setQuantidade(qt);
			  estoqueRepository.save(estoque);
			}else{
			   EstoqueEmpreendimento estoque = new EstoqueEmpreendimento();
	           estoque.adicionarProduto(notaProduto.getItens().get(i).getProduto());
	           estoque.setQuantidade(notaProduto.getItens().get(i).getQuantidade());
	           List<Empreendimento> empre = empreendimento.buscarTodos();
	           estoque.setEmpreendimento(empre.get(0));
	           salvarOuEditar(estoque);
		    }
		}
			
	}
	
	
	
	public boolean existeProduto(Long id)
	{
	  return estoqueRepository.existeProduto(id);
	}
	  
}
