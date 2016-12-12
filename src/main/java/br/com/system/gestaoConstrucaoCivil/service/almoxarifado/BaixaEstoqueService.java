package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.EstoqueEmpreendimento;
import br.com.system.gestaoConstrucaoCivil.pojo.EntradaOuBaixa;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.EstoqueEmpreendimentoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class BaixaEstoqueService {

	@Autowired
	private EstoqueEmpreendimentoRepository estoqueRepository;
	
	
	@Transactional(readOnly = false)
	public void baixar(EntradaOuBaixa baixa) {
       
		
		EstoqueEmpreendimento estoque = estoqueRepository.estoque(baixa.getEmpreendimento().getId(),baixa.getProduto().getId());
		
		if(estoque != null)
		{
		 estoque.setQuantidade(estoque.getQuantidade() - baixa.getQuantidade());
	    
		if(estoque.isNegativo())
	    {
		  estoqueRepository.save(estoque);
	    }else
	    {
	    	throw new EstoqueEmpreendimentoException("Estoque negativo");
	    }
		
		}else
		{
			throw new EstoqueEmpreendimentoException("Produto não encontrado no estoque do empreendimento");
			
		}
	
	}
}
