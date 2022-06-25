package br.com.app.estoque.domain.model;

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
@SequenceGenerator(name = "area_produto_id_seq", sequenceName = "area_produto_id_seq", allocationSize = 1, schema = "communs")
@Table(name = "area_produto", schema = "communs")
public class AreaProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "area_produto_id_seq")
	private Long id;

	@Column(nullable = false)
	private boolean ativo;
	private String descricao;

}
