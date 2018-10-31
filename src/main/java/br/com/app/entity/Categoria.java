package br.com.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "categoria_id_seq", sequenceName = "categoria_id_seq",allocationSize = 1, schema = "communs")
@NamedEntityGraph(name = "Categoria.detail",attributeNodes = {@NamedAttributeNode(value = "categoria")})
@Table(name = "categoria", schema = "communs")
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_id_seq")
	private Long id;
	@Column(nullable = false)
	private boolean ativo;
	@NotNull
	private String descricao;
	@ManyToOne
	private Categoria categoria;
	
	public Categoria() {		
	}
	
	public Categoria(Long id, String descricao, Categoria categoria) {
		this.id = id;
		this.ativo = true;
		this.descricao = descricao;
		this.categoria = categoria;
	}
}
