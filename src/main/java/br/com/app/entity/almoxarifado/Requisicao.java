package br.com.app.entity.almoxarifado;

import java.util.Collection;
import java.util.Date;

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

import br.com.app.entity.Empreendimento;
import br.com.app.entity.almoxarifado.interfaces.EntradaOuBaixa;
import br.com.app.enuns.StatusRequisicao;
import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "requisicao_id_seq", sequenceName = "requisicao_id_seq", allocationSize = 1, schema = "almoxarifado")
@Table(name = "requisicao", schema = "almoxarifado")
public class Requisicao implements EntradaOuBaixa<RequisicaoItem> {

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

	@OneToMany
	private Collection<RequisicaoItem> itens;

	@Override
	public Empreendimento empreendimentoSaida() {

		return empreendimento;
	}

	@Override
	public Empreendimento empreendimentoEntrada() {

		return empreendimento;
	}

}
