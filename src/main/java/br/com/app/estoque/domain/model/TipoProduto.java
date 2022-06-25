package br.com.app.estoque.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "tipo_produto_id_seq", sequenceName = "tipo_produto_id_seq", allocationSize = 1, schema = "almoxarifado")
@Table(name = "tipo_produto", schema = "almoxarifado")
public class TipoProduto implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_produto_id_seq")
	private Long id;

	@Column(nullable = false)
	private boolean ativo;

	@Column(nullable = false, length = 30)
	private String descricao;
	

	public TipoProduto(Long id, boolean ativo, String descricao) {
		super();
		this.id = id;
		this.ativo = ativo;
		this.descricao = descricao;
	}
	
	public TipoProduto() {
		super();
	}
	
	

}
