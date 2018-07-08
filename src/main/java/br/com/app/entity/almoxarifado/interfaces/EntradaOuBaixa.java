package br.com.app.entity.almoxarifado.interfaces;

import java.util.Collection;

import br.com.app.entity.Empreendimento;

public interface EntradaOuBaixa<E> {

	Collection<E> getItens();

	Empreendimento empreendimentoSaida();

	Empreendimento empreendimentoEntrada();
}
