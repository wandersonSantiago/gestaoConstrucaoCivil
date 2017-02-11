package br.com.system.gestaoConstrucaoCivil.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.data.jpa.domain.AbstractPersistable;

import br.com.system.gestaoConstrucaoCivil.enuns.TipoModulo;

@Entity(name = "permissao")
public class Permissao extends AbstractPersistable<Long>{

	private String descricao;
	private String rol;
	@Enumerated(EnumType.STRING)
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
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
}
