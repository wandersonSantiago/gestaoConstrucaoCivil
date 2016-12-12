package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@SequenceGenerator(name = "item_cotacao_id_seq", sequenceName = "item_cotacao_id_seq", initialValue = 1, allocationSize = 1)
@Table(name = "cotacao_item")
public class CotacaoItem implements Serializable {

	
    @Id	
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "item_cotacao_id_seq")
    private Long id;
	@Column(nullable = false)
	private String descricao;
	@Column(nullable = false)
	private Integer quantidade;
	
    @JsonIgnore
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "id_cotacao")
	private Cotacao cotacao;
    
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Cotacao getCotacao() {
		return cotacao;
	}

	public void setContacao(Cotacao cotacao) {
		this.cotacao = cotacao;
	}
	
}
