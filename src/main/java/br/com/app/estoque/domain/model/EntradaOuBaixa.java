package br.com.app.estoque.domain.model;

import java.util.Collection;

import br.com.app.commons.domain.model.Empreendimento;

public interface EntradaOuBaixa<E> {

	Collection<E> getItens();

	Empreendimento empreendimentoSaida();

	Empreendimento empreendimentoEntrada();
}
