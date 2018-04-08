package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.system.gestaoConstrucaoCivil.enuns.CotacaoEmpresaItemStatus;

@Entity
@SequenceGenerator(name = "cotacao_empresa_item_id_seq",
sequenceName = "cotacao_empresa_item_id_seq",
initialValue = 1,
allocationSize = 1)
@Table(name = "cotacao_empresa_item" , schema = "almoxarifado")
public class CotacaoEmpresaItem implements Serializable {

	private static final long serialVersionUID = 1L;

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
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_cotacao_empresa",nullable = false)
	private CotacaoEmpresa cotacaoEmpresa;

	@Enumerated(EnumType.STRING)
	private CotacaoEmpresaItemStatus status;
	
	
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
    
	
	public CotacaoEmpresaItemStatus getStatus() {
		return status;
	}

	public void setStatus(CotacaoEmpresaItemStatus status) {
		this.status = status;
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
