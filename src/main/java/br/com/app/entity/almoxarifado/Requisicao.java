package br.com.app.entity.almoxarifado;

import java.io.Serializable;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.app.entity.Empreendimento;
import br.com.app.enuns.StatusRequisicao;

@Entity
@SequenceGenerator(name = "requisicao_id_seq", sequenceName = "requisicao_id_seq", schema = "almoxarifado")
@Table(name = "requisicao", schema = "almoxarifado")
public class Requisicao implements Serializable {

	private static final long serialVersionUID = 1L;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumeroRequisicao() {
		return numeroRequisicao;
	}

	public void setNumeroRequisicao(Integer numeroRequisicao) {
		this.numeroRequisicao = numeroRequisicao;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public StatusRequisicao getStatusRequisicao() {
		return statusRequisicao;
	}

	public void setStatusRequisicao(StatusRequisicao statusRequisicao) {
		this.statusRequisicao = statusRequisicao;
	}

	public Empreendimento getEmpreendimento() {
		return empreendimento;
	}

	public void setEmpreendimento(Empreendimento empreendimento) {
		this.empreendimento = empreendimento;
	}

}
