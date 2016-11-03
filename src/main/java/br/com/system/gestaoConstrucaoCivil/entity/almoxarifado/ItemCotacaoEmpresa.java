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

@Entity
@SequenceGenerator(name = "cotacao_empresa_item_id_seq",
sequenceName = "cotacao_empresa_item_id_seq",
initialValue = 1,
allocationSize = 1)
@Table(name = "item_cotacao_empresa")
public class ItemCotacaoEmpresa implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cotacao_empresa_item_id_seq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_item",nullable = false)
	private ItemCotacao item;
	
	@Column(nullable = false)
	private Double valor;
	
	@ManyToOne
	@JoinColumn(name = "id_cotacao_empresa",nullable = false)
	private CotacaoEmpresa cotacaoEmpresa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ItemCotacao getItem() {
		return item;
	}

	public void setItem(ItemCotacao item) {
		this.item = item;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public CotacaoEmpresa getCotacaoEmpresa() {
		return cotacaoEmpresa;
	}

	public void setCotacaoEmpresa(CotacaoEmpresa cotacaoEmpresa) {
		this.cotacaoEmpresa = cotacaoEmpresa;
	}
	
	

}
