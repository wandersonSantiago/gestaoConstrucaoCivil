package br.com.app.financeiro.domain.model;

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

import br.com.app.financeiro.domain.enuns.CotacaoEmpresaItemStatus;
import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "cotacao_empresa_item_id_seq", sequenceName = "cotacao_empresa_item_id_seq", allocationSize = 1, schema = "almoxarifado")
@Table(name = "cotacao_empresa_item", schema = "almoxarifado")
public class CotacaoEmpresaItem implements Serializable {

	private static final long serialVersionUID = -446219769994472745L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cotacao_empresa_item_id_seq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_item", nullable = false)
	private CotacaoItem item;

	@Column(name = "observacao")
	private String observacao;
	@Column(nullable = false)
	private Double valorUnitario;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_cotacao_empresa", nullable = false)
	private CotacaoEmpresa cotacaoEmpresa;

	@Enumerated(EnumType.STRING)
	private CotacaoEmpresaItemStatus status;
	
	public Double getValorTotalItem() {
		return item.getQuantidade() * valorUnitario;
	}

}
