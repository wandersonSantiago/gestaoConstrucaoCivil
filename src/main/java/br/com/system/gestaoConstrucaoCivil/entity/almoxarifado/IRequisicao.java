package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import java.util.List;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.interfaces.EntradaOuSaida;

public interface IRequisicao<E> extends EntradaOuSaida{

	public List<E> getItens();
	public InformacaoRequisicao getInformacaoRequisicao();
}
