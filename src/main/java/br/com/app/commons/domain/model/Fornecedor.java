package br.com.app.commons.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.app.estoque.domain.model.Produto;
import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "fornecedor_id_seq", sequenceName = "fornecedor_id_seq", allocationSize = 1, schema = "almoxarifado")
@Table(name = "fornecedor", schema = "almoxarifado")
public class Fornecedor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fornecedor_id_seq")
	private Long id;
	@Column(nullable = false)
	private boolean ativo;
	@Column(length = 50)
	private String contato;
	@Column( length = 50)
	private String observacao;

	@OneToOne
	@JoinColumn(name = "id_dado_empresa", nullable = false)
	private DadoEmpresa dadoEmpresa;
	
	@ManyToMany
	@JoinTable(name="tb_fornecedor_produto", 
		joinColumns = @JoinColumn(name="fornecedor_id"), 
		inverseJoinColumns = @JoinColumn(name="produto_id"))
	private List<Produto> produtos = new ArrayList<>();

}