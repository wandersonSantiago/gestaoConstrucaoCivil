package br.com.app.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.almoxarifado.EstoqueEmpreendimento;
import br.com.app.entity.almoxarifado.NotaFiscalItem;
import br.com.app.entity.almoxarifado.NotaFiscalProduto;
import br.com.app.pojo.SessionUsuario;
import br.com.app.repository.almoxarifado.NotaFiscalProdutoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class CalculaEstoqueService {

	@Autowired
	private NotaFiscalProdutoRepository notaFiscalProdutoRepository; 
	public  List<EstoqueEmpreendimento> calcular(Collection<EstoqueEmpreendimento> estoques) {
		
		List<EstoqueEmpreendimento> newEstoques = new ArrayList<>();
		Long idEmpreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();
		List<NotaFiscalProduto> notas = notaFiscalProdutoRepository.buscarNotaPorEmpreendimento(idEmpreendimento);
		
		estoques.forEach(estoque->{
			custoMedio(estoque,notas);
			total(estoque);
			newEstoques.add(estoque);
			
		});
		return newEstoques;
		
		
	}
	private void custoMedio(EstoqueEmpreendimento estoque,List<NotaFiscalProduto> notas) {
		
		Double valorTotal = 0.0;
		Integer quantidadeTotal = 0;
		
		 
		for (NotaFiscalProduto notaFiscal : notas) {

			for (NotaFiscalItem item : notaFiscal.getItens()) {
				
				if (item.getProduto().getId().equals(estoque.getProduto().getId())) {
					
					valorTotal += item.getValorTotal();
					quantidadeTotal += item.getQuantidade();
				}
			}
		}
		if(quantidadeTotal > 0)
		{
			estoque.setCustoMedio(valorTotal / quantidadeTotal);
		}else {
			estoque.setCustoMedio(0.0);
		}
	}
	private void total(EstoqueEmpreendimento estoque) {
		estoque.setValorTotal(estoque.getCustoMedio() * estoque.getQuantidade());
	}
	 
}
