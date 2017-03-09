package br.com.system.gestaoConstrucaoCivil.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "cargo")
public class Cargo extends AbstractPersistable<Long> {

	
	
	@NotEmpty(message = "{NotNull.descricao}")
	@Size(
			//message = "A descrição deve ter entre {min} e {max} caracteres",
			min = 3,
            max = 25
			)
	
	@NotBlank(message = "OK TESTE")
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
