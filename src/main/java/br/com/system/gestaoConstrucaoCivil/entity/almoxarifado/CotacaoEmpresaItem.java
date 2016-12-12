package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@SequenceGenerator(name = "cotacao_empresa_item_id_seq",
sequenceName = "cotacao_empresa_item_id_seq",
initialValue = 1,
allocationSize = 1)
@Table(name = "cotacao_empresa_item")
public class CotacaoEmpresaItem implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cotacao_empresa_item_id_seq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_item",nullable = false)
	private CotacaoItem item;
	
	@Column(name="observacao")
	private String observaocao;
	@Column(nullable = false)
	private Double valorUnitario;
	
	@ManyToOne
	@JoinColumn(name = "id_cotacao_empresa",nullable = false)
	private CotacaoEmpresa cotacaoEmpresa;

	@Transient
	private boolean ganhou  = true;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CotacaoItem getItem() {
		return item;
	}

	public void setItem(CotacaoItem item) {
		this.item = item;
	}
    
	
	public boolean isGanhou() {
		return ganhou;
	}

	public void setGanhou(boolean ganhou) {
		this.ganhou = ganhou;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}
	public String getObservaocao() {
		return observaocao;
	}

	public void setObservaocao(String observaocao) {
		this.observaocao = observaocao;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public CotacaoEmpresa getCotacaoEmpresa() {
		return cotacaoEmpresa;
	}

	public void setCotacaoEmpresa(CotacaoEmpresa cotacaoEmpresa) {
		this.cotacaoEmpresa = cotacaoEmpresa;
	}


		
	
}
