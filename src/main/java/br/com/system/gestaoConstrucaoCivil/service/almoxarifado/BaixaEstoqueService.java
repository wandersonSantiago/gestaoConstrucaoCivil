package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.EstoqueEmpreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.interfaces.EntradaOuSaida;
import br.com.system.gestaoConstrucaoCivil.pojo.EntradaOuBaixa;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.EstoqueEmpreendimentoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class BaixaEstoqueService {

	@Autowired
	private EstoqueEmpreendimentoRepository estoqueRepository;
	
	@Transactional(readOnly = false)
	public void baixar(EntradaOuSaida baixa) {
       
		
		for(EntradaOuBaixa item : baixa.itens())
		{
			EstoqueEmpreendimento estoque = estoqueRepository.estoque(item.getEmpreendimento().getId(),item.getProduto().getId());
			if(estoque != null)
			{
			 estoque.setQuantidade(estoque.getQuantidade() - item.getQuantidade());
		    
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
	
	
}
