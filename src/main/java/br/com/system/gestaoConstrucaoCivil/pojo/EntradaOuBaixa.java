package br.com.system.gestaoConstrucaoCivil.pojo;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Produto;

public class EntradaOuBaixa {

	private Produto produto;
	private Integer quantidade;
	private Empreendimento empreendimento;
	public EntradaOuBaixa(Produto produto ,Integer quantidade,Empreendimento empreendimento)
	{
		this.produto = produto;
		this.quantidade = quantidade;
		this.empreendimento = empreendimento;
	}
  
	public Produto getProduto() {
		return produto;
	}

	
	public Integer getQuantidade() {
		return quantidade;
	}

	public Empreendimento getEmpreendimento() {
		return empreendimento;
	}
}
