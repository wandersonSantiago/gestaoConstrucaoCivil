package br.com.system.gestaoConstrucaoCivil.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="cliente_morador_outros" , schema = "communs")
public class ClienteMoradorOutros extends ClienteMorador implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@NotEmpty
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
