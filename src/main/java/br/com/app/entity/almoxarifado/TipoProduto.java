package br.com.app.entity.almoxarifado;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@SequenceGenerator(name = "tipo_produto_id_seq", sequenceName = "tipo_produto_id_seq", schema = "almoxarifado", initialValue = 1, allocationSize = 1)
@Table(name = "tipo_produto", schema = "almoxarifado")
public class TipoProduto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_produto_id_seq")
	private Long id;

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private boolean ativo;

	@Column(nullable = false, length = 30)
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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
