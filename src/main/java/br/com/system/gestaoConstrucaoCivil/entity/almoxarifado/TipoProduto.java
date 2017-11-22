package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "tipo_produto", schema="almoxarifado")
public class TipoProduto  extends AbstractPersistable<Long> implements Serializable  {

	
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private boolean ativo;
	
	@Column(nullable = false,length = 30)
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
