package br.com.system.gestaoConstrucaoCivil.entity;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import br.com.system.gestaoConstrucaoCivil.enuns.TipoModulo;

@Entity(name = "permissao")
public class Permissao extends AbstractPersistable<Long>{

	private String descricao;
	private TipoModulo tipoModulo;
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public TipoModulo getTipoModulo() {
		return tipoModulo;
	}
	public void setTipoModulo(TipoModulo tipoModulo) {
		this.tipoModulo = tipoModulo;
	}
	
	
	
	
	
}
