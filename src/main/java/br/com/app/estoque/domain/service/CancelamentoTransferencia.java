package br.com.app.estoque.domain.service;

import java.util.Collection;

import br.com.app.commons.domain.model.Empreendimento;
import br.com.app.estoque.domain.model.EntradaOuBaixa;
import br.com.app.estoque.domain.model.Transferencia;
import br.com.app.estoque.domain.model.TransferenciaItem;


public class CancelamentoTransferencia implements EntradaOuBaixa<TransferenciaItem> {

	private Transferencia transferencia;
	
	 public CancelamentoTransferencia(Transferencia transferencia ) {
		this.transferencia = transferencia;
	}
	@Override
	public Collection<TransferenciaItem> getItens() {
		 
		return transferencia.getItens();
	}
	@Override
	public Empreendimento empreendimentoSaida() {
		
		return transferencia.getEmpreendimentoDestino();
	}
	@Override
	public Empreendimento empreendimentoEntrada() {
		
		return transferencia.getNotaFiscal().getEmpreendimento();
	}
	
	
}
