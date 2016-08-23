package br.com.system.gestaoConstrucaoCivil.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.gestaoConstrucaoCivil.enuns.TipoNotaEnum;

@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal extends AbstractPersistable<Long>{

	private TipoNotaEnum tipoNota;
	private Integer numero;
	private Integer chaveAcesso;
	private Date dataNota;
	private Date dataVencimento;
	private Integer serie;
	private Fornecedor fornecedor;
	private String observacao;
	private Double valorTotal;
	
	@OneToMany(mappedBy = "notaFiscal" , cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<ItemNotaFiscal> itens;

	public TipoNotaEnum getTipoNota() {
		return tipoNota;
	}
    
	public Integer getChaveAcesso() {
		return chaveAcesso;
	}

	public void setChaveAcesso(Integer chaveAcesso) {
		this.chaveAcesso = chaveAcesso;
	}

	public void setTipoNota(TipoNotaEnum tipoNota) {
		this.tipoNota = tipoNota;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Date getDataNota() {
		return dataNota;
	}

	public void setDataNota(Date dataNota) {
		this.dataNota = dataNota;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	

	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<ItemNotaFiscal> getItens() {
		return itens;
	}

	public void setItens(List<ItemNotaFiscal> itens) {
		this.itens = itens;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Integer getSerie() {
		return serie;
	}

	public void setSerie(Integer serie) {
		this.serie = serie;
	}
	
	
	
}
