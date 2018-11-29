package br.com.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.app.enuns.CategoriaEnum;
import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "categoria_financeiro_id_seq", sequenceName = "categoria_financeiro_id_seq",allocationSize = 1, schema = "communs")
@Table(name = "categoria_financeiro", schema = "communs")
public class CategoriaFinanceiro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_financeiro_id_seq")
	private Long id;
	@Column(nullable = false)
	private boolean ativo;
	@NotNull
	private String descricao;
	@ManyToOne
	private CategoriaFinanceiro categoria;
	
	@Enumerated(EnumType.STRING)
	private CategoriaEnum tipo;
	
	public CategoriaFinanceiro() {		
	}
	
	public CategoriaFinanceiro(Long id, String descricao, CategoriaFinanceiro categoria) {
		this.id = id;
		this.ativo = true;
		this.descricao = descricao;
		this.categoria = categoria;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao.toUpperCase();
	}
}
