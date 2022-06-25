package br.com.app.estoque.domain.model;

public interface IEstoqueBuilder {

	IEstoqueBuilder produto(Produto produto);
	IEstoqueBuilder quantidade(Integer quantidade);
	EstoqueEmpreendimento build(); 
}
