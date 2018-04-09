package br.com.app.entity.almoxarifado;

import br.com.app.pojo.SessionUsuario;

public class EstoqueBuilder implements IEstoqueBuilder{

	 
	private Produto produto;
	private Integer quantidade;
 	
	@Override
	public IEstoqueBuilder produto(Produto produto) {
		this.produto = produto;
		return this;
	}

 	@Override
	public IEstoqueBuilder quantidade(Integer quantidade) {
		this.quantidade = quantidade;
		return this;
	}
 	
 	
	@Override
	public EstoqueEmpreendimento build() {
		EstoqueEmpreendimento estoque = new EstoqueEmpreendimento();
		estoque.setProduto(this.produto);
		estoque.setQuantidade(this.quantidade);
		estoque.setEmpreendimento(SessionUsuario.getInstance().getUsuario().getEmpreendimento());
		return estoque;
	}
 
 

}
