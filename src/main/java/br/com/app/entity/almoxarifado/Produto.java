package br.com.app.entity.almoxarifado;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonView;

import br.com.app.entity.Categoria;
import br.com.app.entity.almoxarifado.View.Summary;
import br.com.app.enuns.UnidadeMedidaEnum;

@Entity
@SequenceGenerator(name = "produto_id_seq", sequenceName = "produto_id_seq", schema = "almoxarifado")
@NamedEntityGraph(name = "Produto.detail", attributeNodes = { @NamedAttributeNode("categoria"),
		@NamedAttributeNode("fabricante"), @NamedAttributeNode("tipoProduto") })

@Table(name = "produto", schema = "almoxarifado")
@JsonIgnoreType
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_id_seq")
	private Long id;

	@JsonView(Summary.class)
	@Column(nullable = true, unique = true)
	private Integer codigo;

	@JsonView(Summary.class)
	@Column(nullable = false, length = 50, unique = true)
	private String codigoBarra;

	@JsonView(Summary.class)
	@Column(nullable = false, length = 50)
	private String descricao;
	@Column(nullable = true)
	private boolean ativo;
	@JsonIgnore
	@Enumerated(EnumType.STRING)
	private UnidadeMedidaEnum unidadeMedida;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_categoria", nullable = true)
	private Categoria categoria;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "produtos_fornecedores", schema = "almoxarifado", joinColumns = @JoinColumn(name = "id_produto"), inverseJoinColumns = @JoinColumn(name = "id_fornecedor"))
	@Fetch(FetchMode.SUBSELECT)
	private List<Fornecedor> fornecedores;

	@ManyToOne
	@JoinColumn(name = "id_fabricante", nullable = true)
	private Fabricante fabricante;

	@ManyToOne
	@JoinColumn(name = "id_tipo", nullable = true)
	private TipoProduto tipoProduto;

	@Transient
	private boolean geraCodigoBarra = true;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isGeraCodigoBarra() {
		return geraCodigoBarra;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
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

	public UnidadeMedidaEnum getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedidaEnum unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

}