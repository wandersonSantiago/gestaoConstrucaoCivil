package br.com.app.pojo;

import java.util.Collection;

import br.com.app.entity.Empreendimento;
import br.com.app.entity.almoxarifado.Transferencia;
import br.com.app.entity.almoxarifado.TransferenciaItem;
import br.com.app.entity.almoxarifado.interfaces.EntradaOuBaixa;


public class CancelamentoTransferencia implements EntradaOuBaixa<TransferenciaItem> {

	private Transferencia transferencia;
	
	 public CancelamentoTransferencia(Transferencia transferencia ) {
		this.transferencia = transferencia;
	}
	@Override
	public Collection<TransferenciaItem> getItens() {
		// TODO Auto-generated method stub
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
