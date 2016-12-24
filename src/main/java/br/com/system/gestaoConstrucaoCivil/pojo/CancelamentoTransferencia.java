package br.com.system.gestaoConstrucaoCivil.pojo;

import java.util.Collection;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Transferencia;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.TransferenciaItem;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.interfaces.EntradaOuBaixa;


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
