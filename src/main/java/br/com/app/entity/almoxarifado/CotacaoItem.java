package br.com.app.entity.almoxarifado;

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

import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "item_cotacao_id_seq", sequenceName = "item_cotacao_id_seq", allocationSize = 1, schema = "almoxarifado")
@Table(name = "cotacao_item", schema = "almoxarifado")
public class CotacaoItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_cotacao_id_seq")
	private Long id;
	@Column(nullable = false)
	private String descricao;
	@Column(nullable = false)
	private Integer quantidade;

	@JsonIgnore
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "id_cotacao")
	private Cotacao cotacao;

}
