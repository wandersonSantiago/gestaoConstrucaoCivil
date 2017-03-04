package br.com.system.gestaoConstrucaoCivil.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name="cliente_morador_casa")
public class ClienteMoradorCasa extends ClienteMorador implements Serializable{
	

	@Column(nullable = false, length = 10)
	private Integer numeroCasa;

	public Integer getNumeroCasa() {
		return numeroCasa;
	}

	public void setNumeroCasa(Integer numeroCasa) {
		this.numeroCasa = numeroCasa;
	}

	

	
}
