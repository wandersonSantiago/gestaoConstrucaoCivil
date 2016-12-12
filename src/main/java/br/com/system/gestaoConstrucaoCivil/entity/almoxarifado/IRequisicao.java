package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import java.util.List;

public interface IRequisicao<E>{

	public List<E> getItens();
	public InformacaoRequisicao getInformacaoRequisicao();
}
