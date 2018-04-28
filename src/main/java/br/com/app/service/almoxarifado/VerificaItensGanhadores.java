package br.com.app.service.almoxarifado;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.almoxarifado.Cotacao;
import br.com.app.entity.almoxarifado.CotacaoEmpresaItem;
import br.com.app.enuns.CotacaoEmpresaItemStatus;
import br.com.app.repository.almoxarifado.CotacaoEmpresaItemRepository;

@Service
public class VerificaItensGanhadores {

	private List<CotacaoEmpresaItem> itensVerifica = new ArrayList<CotacaoEmpresaItem>();
	
	private Integer chamada = 0;
	
	@Autowired
	private CotacaoEmpresaItemRepository cotaEmpresaItemReposi;

	@Transactional(readOnly = false)
	public void verificarGanhadores(Cotacao cotacao) {
		List<CotacaoEmpresaItem> itens = cotaEmpresaItemReposi.buscarItensPorIdCotacao(cotacao.getId());

		Integer cont = 0;
		
		
		while (cotacao.getItens().size() > this.chamada) {
			CotacaoEmpresaItem itemASerVerificado = itens.get(cont);

			for (CotacaoEmpresaItem item : itens) {
				if (itemASerVerificado.getItem().getId() == item.getItem().getId()) {

					itensVerifica.add(item);
				}
			}
			cont++;
			verificarItens();
			verficarCotacaoEmpresa();
			itensVerifica.clear();
		}
		
	}

	@Transactional(readOnly = false)
	private void verficarCotacaoEmpresa()
	{
		for(CotacaoEmpresaItem item: itensVerifica)
		{
			if(item.getCotacaoEmpresa().getQuantidadeItensGanhos() <= 0)
			{
				item.getCotacaoEmpresa().perdeu();
			}else
			{
				item.getCotacaoEmpresa().ganhou();
			}
		}
	}
	@Transactional(readOnly = false)
	private void verificarItens() {

		CotacaoEmpresaItem itemGanhador = itensVerifica.get(0);
		itemGanhador.setStatus(CotacaoEmpresaItemStatus.GANHOU);

		for (CotacaoEmpresaItem item : itensVerifica) {
			
			if (!itemGanhador.equals(item)) {
				
				if (itemGanhador.getValorUnitario() > item.getValorUnitario()) {
					
					itemGanhador.setStatus(CotacaoEmpresaItemStatus.PERDEU);
					item.setStatus(CotacaoEmpresaItemStatus.GANHOU);
					itemGanhador = item;
				
				} else if (itemGanhador.getValorUnitario().equals(item.getValorUnitario())) {
					
					if(itemGanhador.getCotacaoEmpresa().getQuantidadeItensGanhos() >= item.getCotacaoEmpresa().getQuantidadeItensGanhos())
					{
						item.setStatus(CotacaoEmpresaItemStatus.PERDEU);
					}else
					{
						item.setStatus(CotacaoEmpresaItemStatus.PERDEU);
					}
					
				} else {
					item.setStatus(CotacaoEmpresaItemStatus.PERDEU);
				}
			}
		}
		
		
		chamada++;

	}
	
}
