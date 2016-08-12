package br.com.system.gestaoConstrucaoCivil.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "config_empreendimento_casa")
public class ConfigEmpreendimentoCasa extends ConfigEmpreendimento implements Serializable {

	@Column(nullable = false, length = 10)
	private Integer quantidadeCasa;

	@Column(nullable = false, length = 10)
	private Integer quantidadeAndarPorCasa;

	public Integer getQuantidadeCasa() {
		return quantidadeCasa;
	}

	public void setQuantidadeCasa(Integer quantidadeCasa) {
		this.quantidadeCasa = quantidadeCasa;
	}

	public Integer getQuantidadeAndarPorCasa() {
		return quantidadeAndarPorCasa;
	}

	public void setQuantidadeAndarPorCasa(Integer quantidadeAndarPorCasa) {
		this.quantidadeAndarPorCasa = quantidadeAndarPorCasa;
	}

	
}