package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "baixa_estoque_casa")
public class BaixaEstoqueCasa extends BaixaEstoque implements Serializable{

	@Column(nullable = false)
	private Integer andar;
	
	@Column(nullable = false)
	private Integer casa;

	public Integer getAndar() {
		return andar;
	}

	public void setAndar(Integer andar) {
		this.andar = andar;
	}

	public Integer getCasa() {
		return casa;
	}

	public void setCasa(Integer casa) {
		this.casa = casa;
	}
	
	
}
