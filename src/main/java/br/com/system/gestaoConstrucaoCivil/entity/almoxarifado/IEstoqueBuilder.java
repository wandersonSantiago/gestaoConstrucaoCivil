package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

public interface IEstoqueBuilder {

	IEstoqueBuilder produto(Produto produto);
	IEstoqueBuilder quantidade(Integer quantidade);
	EstoqueEmpreendimento build(); 
}
