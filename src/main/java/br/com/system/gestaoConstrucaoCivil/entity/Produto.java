package br.com.system.gestaoConstrucaoCivil.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;


@Entity
@Table(name = "produto")
public class Produto extends AbstractPersistable<Long>{

	@Column(nullable = false,length = 50)
	private String codigoBarra;
	
	private Fornecedor fornecedor;
	@Column(nullable = false,length = 50)
	private String descricao;
	@Column(nullable = false)
	private Double valorCompra;
	@Column(nullable = false)
	private boolean ativo;
	@ManyToOne
	@JoinColumn(name="id_unidade_medida",nullable = false)
	private UnidadeMedida unidadeMedida;
    @ManyToOne
    @JoinColumn(name="id_categoria",nullable = false)
	private Categoria categoria;
	
	
	public String getCodigoBarra() {
		return codigoBarra;
	}
	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
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

}
