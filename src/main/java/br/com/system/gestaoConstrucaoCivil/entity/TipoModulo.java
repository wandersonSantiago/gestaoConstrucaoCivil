package br.com.system.gestaoConstrucaoCivil.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "tipo_modulo")
public class TipoModulo extends AbstractPersistable<Long>{

	
	@Column(nullable = false,length = 50)
	private String descricaoModulo;
	@Column(nullable = false)
	private Integer tipo;
	
	public String getDescricaoModulo() {
		return descricaoModulo;
	}
	public void setDescricaoModulo(String descricaoModulo) {
		this.descricaoModulo = descricaoModulo;
	}
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

}
