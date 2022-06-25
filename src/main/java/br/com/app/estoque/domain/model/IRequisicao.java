package br.com.app.estoque.domain.model;

import java.util.List;

public interface IRequisicao<E> extends EntradaOuBaixa<E> {

	List<E> getItens();

	Requisicao getInformacaoRequisicao();

}
