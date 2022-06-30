package br.com.app.financeiro.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.app.commons.domain.model.Fornecedor;
import br.com.app.financeiro.domain.enuns.CotacaoEmpresaItemStatus;
import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "cotacao_empresa_id_seq", sequenceName = "cotacao_empresa_id_seq", allocationSize = 1, schema = "almoxarifado")
@Table(name = "cotacao_empresa", schema = "almoxarifado")
public class CotacaoEmpresa implements Serializable{

	private static final long serialVersionUID = 6241438706252943282L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cotacao_empresa_id_seq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_cotacao", nullable = false)
	private Cotacao cotacao;

	@ManyToOne
	@JoinColumn(name = "id_fornecedor", nullable = false)
	private Fornecedor fornecedor;

	@OneToMany(mappedBy = "cotacaoEmpresa", cascade = CascadeType.ALL)
	@Column(nullable = false)
	private List<CotacaoEmpresaItem> itens = new ArrayList<>();

	@Transient
	private List<CotacaoEmpresaItem> itensTotal = new ArrayList<>();

	@Transient
	private Integer quantidade = 0;
	
	@Transient
	private Integer quantidadeTotal = 0;

	private Boolean ganhou;
	
	@Transient
	private double valorTotal;
	
	@Transient
	private double valorTotalGanho;

	
	public void removerItensPerdedores() {
		this.quantidadeTotal = this.itens.size();
		this.itensTotal.addAll(itens);
		List<CotacaoEmpresaItem> itensParaRemover = new ArrayList<CotacaoEmpresaItem>();
		itens.forEach(item -> {

			if (!item.getStatus().equals(CotacaoEmpresaItemStatus.GANHOU)) {
				if(item.getCotacaoEmpresa().getCotacao().isPorItem()) {
					itensParaRemover.add(item);
				}
			}
		});
		itens.removeAll(itensParaRemover);
		
		//itens.removeIf((CotacaoEmpresaItem item) -> item.getStatus().equals(CotacaoEmpresaItemStatus.PERDEU));

	}

	public Integer getQuantidadeItensGanhos() {

		itens.forEach(item -> {

			if (item.getStatus().equals(CotacaoEmpresaItemStatus.GANHOU)) {
				this.quantidade++;
			}
		});
		return quantidade;
	}

	public double getValorGanho() {
		valorTotalGanho = 0.0;
		itens.forEach(item -> {
			if (item.getStatus().equals(CotacaoEmpresaItemStatus.GANHOU)) {
				valorTotalGanho = valorTotalGanho + (item.getItem().getQuantidade() * item.getValorUnitario());
			}
		});
		return valorTotalGanho;
	}
	
	public double getValorTotal() {
		valorTotal = 0.0;
		itensTotal = itensTotal.size() == 0 ? itens : itensTotal;
		itensTotal.forEach(item -> {
				valorTotal =  valorTotal + (item.getItem().getQuantidade() * item.getValorUnitario());
		});
		return valorTotal;
	}
	
	public int getQuantidade() {
		return quantidadeTotal == 0 ? this.itens.size() : quantidadeTotal;
	}
	
	public void perdeu() {
		this.ganhou = false;

	}

	public void ganhou() {
		this.ganhou = true;
	}

	public Boolean getGanhou() {
		return this.ganhou;
	}

}
