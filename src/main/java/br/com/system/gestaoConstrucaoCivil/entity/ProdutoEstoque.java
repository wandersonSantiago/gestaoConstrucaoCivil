package br.com.system.gestaoConstrucaoCivil.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;


@Entity
@Table(name = "produto_estoque")
public class ProdutoEstoque extends AbstractPersistable<Long>{
 
	
	private Integer quantidade;
	private Empreendimento empreendimento;
	private Produto protudo;
	
	
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Empreendimento getEmpreendimento() {
		return empreendimento;
	}
	public void setEmpreendimento(Empreendimento empreendimento) {
		this.empreendimento = empreendimento;
	}
	public Produto getProtudo() {
		return protudo;
	}
	public void setProtudo(Produto protudo) {
		this.protudo = protudo;
	}
}
