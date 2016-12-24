package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import java.util.List;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.interfaces.EntradaOuBaixa;

public interface IRequisicao<E> extends EntradaOuBaixa{

	public List<E> getItens();
	public InformacaoRequisicao getInformacaoRequisicao();
}
