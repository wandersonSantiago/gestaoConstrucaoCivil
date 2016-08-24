package br.com.system.gestaoConstrucaoCivil.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "produto")
public class Produto extends AbstractPersistable<Long> {

	@Column(nullable = false, length = 50)
	private String codigoBarra;

	@Column(nullable = false, length = 50)
	private String descricao;
	@Column(nullable = false)
	private Double valorCompra;
	@Column(nullable = false)
	private boolean ativo;
	@ManyToOne
	@JoinColumn(name = "id_unidade_medida", nullable = true)
	private UnidadeMedida unidadeMedida;
	@ManyToOne
	@JoinColumn(name = "id_categoria", nullable = true)
	private Categoria categoria;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "produtos_fornecedores", joinColumns = @JoinColumn(name = "id_produto"), 
	inverseJoinColumns = @JoinColumn(name = "id_fornecedor"))
	private List<Fornecedor> fornecedores;

	@Column(nullable = false)
	private Integer quantidadeMaxima;
	
	@Column(nullable = false)
	private Integer quantidadeMinima;
	
	
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

	public Double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(Double valorCompra) {
		this.valorCompra = valorCompra;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
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

	public Integer getQuantidadeMaxima() {
		return quantidadeMaxima;
	}

	public void setQuantidadeMaxima(Integer quantidadeMaxima) {
		this.quantidadeMaxima = quantidadeMaxima;
	}

	public Integer getQuantidadeMinima() {
		return quantidadeMinima;
	}

	public void setQuantidadeMinima(Integer quantidadeMinima) {
		this.quantidadeMinima = quantidadeMinima;
	}
	

}