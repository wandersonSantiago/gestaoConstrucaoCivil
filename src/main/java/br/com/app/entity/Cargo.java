package br.com.app.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@SequenceGenerator(name = "cargo_id_seq", sequenceName = "cargo_id_seq",schema="communs")
@Table(name = "cargo", schema = "communs")
public class Cargo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cargo_id_seq")
	private Long id;

	@NotEmpty(message = "{NotNull.descricao}")
	@Size(
			// message = "A descrição deve ter entre {min} e {max} caracteres",
			min = 3, max = 25)

	@NotBlank(message = "OK TESTE")
	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
