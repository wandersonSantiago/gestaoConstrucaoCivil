package br.com.system.gestaoConstrucaoCivil.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.system.gestaoConstrucaoCivil.enuns.TipoCategoriaEnum;

@Entity
@Table(name = "categoria" , schema = "communs")
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(nullable = false)
	private boolean ativo;

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 25)
	private String descricao;

	@NotBlank
	@Enumerated(EnumType.STRING)
	TipoCategoriaEnum tipoCategoria;
	
	
	

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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public TipoCategoriaEnum getTipoCategoria() {
		return tipoCategoria;
	}

	public void setTipoCategoria(TipoCategoriaEnum tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}
}
