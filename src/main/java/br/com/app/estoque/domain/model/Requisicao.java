package br.com.app.estoque.domain.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.app.commons.domain.model.Empreendimento;
import br.com.app.commons.domain.model.Estrutura;
import br.com.app.estoque.domain.enuns.StatusRequisicao;
import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "requisicao_id_seq", sequenceName = "requisicao_id_seq", allocationSize = 1, schema = "almoxarifado")
@Table(name = "requisicao", schema = "almoxarifado")
public class Requisicao implements Serializable, EntradaOuBaixa<RequisicaoItem> {


	private static final long serialVersionUID = -5183740797844937166L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "requisicao_id_seq")
	private Long id;

	@Column(name = "numero_requisicao")
	private Integer numeroRequisicao;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_saida")
	private Date dataSaida;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_criacao")
	private Date dataCriacao;

	@Enumerated(EnumType.STRING)
	private StatusRequisicao statusRequisicao;

	@ManyToOne
	@JoinColumn(name = "id_empreendimento")
	private Empreendimento empreendimento;

	@OneToMany(cascade = CascadeType.ALL)
	private Collection<RequisicaoItem> itens;
	
	@ManyToOne
	@JoinColumn(name="id_estrutura")
	private Estrutura estrutura;
	
	private Long idUsuario;
	
	private String observacao;

	@Override
	public Empreendimento empreendimentoSaida() {

		return empreendimento;
	}

	@Override
	public Empreendimento empreendimentoEntrada() {

		return empreendimento;
	}

}
