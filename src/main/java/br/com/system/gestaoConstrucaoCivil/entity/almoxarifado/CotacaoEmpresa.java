package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

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

import br.com.system.gestaoConstrucaoCivil.enuns.CotacaoEmpresaItemStatus;

@Entity
@SequenceGenerator(name = "cotacao_empresa_id_seq", sequenceName = "cotacao_empresa_id_seq", initialValue = 1, allocationSize = 1)
@Table(name = "cotacao_empresa", schema = "almoxarifado")
public class CotacaoEmpresa implements Serializable{

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cotacao_empresa_id_seq")
	private Long id;
	
	
	@ManyToOne
	@JoinColumn(name="id_cotacao",nullable = false)
	private Cotacao cotacao;
	
	@ManyToOne
	@JoinColumn(name="id_fornecedor",nullable = false)
	private Fornecedor fornecedor;
	
	@OneToMany(mappedBy = "cotacaoEmpresa",cascade = CascadeType.ALL)
	@Column(nullable = false)
	private List<CotacaoEmpresaItem> itens;

	@Transient
	private Integer quantidade = 0;
	
	private Boolean ganhou;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cotacao getCotacao() {
		return cotacao;
	}

	public void setCotacao(Cotacao cotacao) {
		this.cotacao = cotacao;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<CotacaoEmpresaItem> getItens() {
		return itens;
	}

	public void setItens(List<CotacaoEmpresaItem> itens) {
		this.itens = itens;
	}
	public void removerItensPerdedores()
	{
		List<CotacaoEmpresaItem> itensParaRemover = new ArrayList<CotacaoEmpresaItem>();
		itens.forEach(item ->{
			
			if(item.getStatus().equals(CotacaoEmpresaItemStatus.PERDEU))
			{
			    itensParaRemover.add(item);
			}
		});
	    itens.removeAll(itensParaRemover);
	}
	public Integer getQuantidadeItensGanhos()
	{
		
		itens.forEach(item -> {
			
			if(item.getStatus().equals(CotacaoEmpresaItemStatus.GANHOU))
			{
				this.quantidade++;
			}
		});
		return quantidade;
	}
	public void perdeu()
	{
		this.ganhou = false;
		
	}
	public void ganhou(){
		this.ganhou = true;
	}
	public Boolean getGanhou()
	{
		return this.ganhou;
	}
	
	
}
