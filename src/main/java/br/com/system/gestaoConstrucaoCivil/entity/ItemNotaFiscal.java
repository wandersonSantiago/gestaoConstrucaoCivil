package br.com.system.gestaoConstrucaoCivil.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import br.com.system.gestaoConstrucaoCivil.enuns.UnidadeMedidaEnum;


@Entity
@Table(name = "item_nota_fiscal")
public class ItemNotaFiscal extends AbstractPersistable<Long>{

	
	@Column(nullable = false)
	private Produto produto;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UnidadeMedidaEnum unidadeMedida;
	@Column(nullable = false)
	private Integer quantidade;
	@Column(nullable = false)
	private Double valorUnitario;
	@Column(nullable = false)
	private Double valorTotal;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "nota_fiscal")
	private NotaFiscal notaFiscal;
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public UnidadeMedidaEnum getUnidadeMedida() {
		return unidadeMedida;
	}
	public void setUnidadeMedida(UnidadeMedidaEnum unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}
	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
	
	
	
}
