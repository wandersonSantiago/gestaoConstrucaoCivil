package br.com.system.gestaoConstrucaoCivil.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "seguranca")
public class Seguranca extends AbstractPersistable<Long>{

	
	@Column(nullable = false,length = 50)
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
