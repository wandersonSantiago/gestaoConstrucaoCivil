package br.com.system.gestaoConstrucaoCivil.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.NotBlank;
@Entity
@Table(name="cliente_morador_casa")
public class ClienteMoradorCasa extends ClienteMorador implements Serializable{
	
	
	@NotBlank
	@Max(10)
	private Integer numeroCasa;

	public Integer getNumeroCasa() {
		return numeroCasa;
	}

	public void setNumeroCasa(Integer numeroCasa) {
		this.numeroCasa = numeroCasa;
	}

	

	
}
