package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.interfaces;

import java.util.Collection;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;

public interface EntradaOuBaixa<E> {

	public Collection<E> getItens();
	public Empreendimento empreendimentoSaida();
	public Empreendimento empreendimentoEntrada();
}
