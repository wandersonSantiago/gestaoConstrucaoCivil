package br.com.app.entity.almoxarifado.interfaces;

import java.util.Collection;

import br.com.app.entity.Empreendimento;

public interface EntradaOuBaixa<E> {

	public Collection<E> getItens();
	public Empreendimento empreendimentoSaida();
	public Empreendimento empreendimentoEntrada();
}
