package br.com.app.entity.almoxarifado;

import java.util.List;

import br.com.app.entity.almoxarifado.interfaces.EntradaOuBaixa;

public interface IRequisicao<E> extends EntradaOuBaixa<E>{

	public List<E> getItens();
	public InformacaoRequisicao getInformacaoRequisicao();
}
