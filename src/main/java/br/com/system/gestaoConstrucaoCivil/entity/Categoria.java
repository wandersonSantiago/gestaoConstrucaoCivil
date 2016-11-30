package br.com.system.gestaoConstrucaoCivil.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import br.com.system.gestaoConstrucaoCivil.enuns.TipoCategoriaEnum;


@Entity
@Table(name = "categoria")
public class Categoria extends AbstractPersistable<Long> implements Serializable {

	
	@Column(nullable = false)
	private boolean ativo;
	
	@Column(nullable = false,length = 30)
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	TipoCategoriaEnum tipoCategoria;
	
	
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
	
	
	public TipoCategoriaEnum getTipoCategoria() {
		return tipoCategoria;
	}
	public void setTipoCategoria(TipoCategoriaEnum tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}
}
