package br.com.app.entity.almoxarifado;

import java.util.List;

import br.com.app.entity.almoxarifado.interfaces.EntradaOuBaixa;

public interface IRequisicao<E> extends EntradaOuBaixa<E> {

	List<E> getItens();

	Requisicao getInformacaoRequisicao();

}
