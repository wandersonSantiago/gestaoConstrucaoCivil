package br.com.system.gestaoConstrucaoCivil.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

import br.com.system.gestaoConstrucaoCivil.enuns.TipoCategoriaEnum;

@Entity
@Table(name = "categoria")
public class Categoria extends AbstractPersistable<Long> implements Serializable {

	@Column(nullable = false)
	private boolean ativo;

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 25)
	private String descricao;

	@NotBlank
	@Enumerated(EnumType.STRING)
	TipoCategoriaEnum tipoCategoria;

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
