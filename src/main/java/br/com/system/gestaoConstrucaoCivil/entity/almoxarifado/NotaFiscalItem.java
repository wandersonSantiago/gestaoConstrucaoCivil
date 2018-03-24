package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "nota_fiscal_item" , schema="almoxarifado")
public class NotaFiscalItem  extends Item implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private Double valorTotal;

	@ManyToOne
	@JoinColumn(name = "id_nota_fiscal")
	private NotaFiscalProduto notaFiscalProduto;

	
	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public NotaFiscalProduto getNotaFiscalProduto() {
		return notaFiscalProduto;
	}

	public void setNotaFiscalProduto(NotaFiscalProduto notaFiscalProduto) {
		this.notaFiscalProduto = notaFiscalProduto;
	}

   
}
