package br.com.system.gestaoConstrucaoCivil.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "seguranca")
public class Seguranca extends AbstractPersistable<Long>{

	
	@Column(nullable = false,length = 50)
	private String descricaoSeguranca;
	
	private TipoModulo tipoModulo;


	public String getDescricaoSeguranca() {
		return descricaoSeguranca;
	}

	public void setDescricaoSeguranca(String descricaoSeguranca) {
		this.descricaoSeguranca = descricaoSeguranca;
	}

	public TipoModulo getTipoModulo() {
		return tipoModulo;
	}

	public void setTipoModulo(TipoModulo tipoModulo) {
		this.tipoModulo = tipoModulo;
	}
}
