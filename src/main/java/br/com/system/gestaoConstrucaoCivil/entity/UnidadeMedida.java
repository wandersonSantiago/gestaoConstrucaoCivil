package br.com.system.gestaoConstrucaoCivil.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "unidade_medida")
public class UnidadeMedida extends AbstractPersistable<Long>{

	
	@Column(nullable = false,length = 50)
	private String descricaoCategoria;
	
	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}
	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}
	
}
