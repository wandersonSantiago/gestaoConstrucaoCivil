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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.app.entity.almoxarifado.View.Summary;
import br.com.app.enuns.StatusRequisicao;
import br.com.app.util.geradorCodigo.GeraNumeroRequisicao;

@Entity
@SequenceGenerator(name = "requisicao_id_seq", sequenceName = "requisicao_id_seq", schema = "almoxarifado")
@Table(name = "requisicao", schema = "almoxarifado")
public class Requisicao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "requisicao_id_seq")
	private Long id;

	@JsonView(Summary.class)
	@Column(name = "numero_requisicao")
	private Integer numeroRequisicao;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_saida")
	private Date dataSaida;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_criacao")
	private Date dataCriacao;

	@JsonView(Summary.class)
	@Enumerated(EnumType.STRING)
	private StatusRequisicao statusRequisicao;

	public Integer getNumeroRequisicao() {
		return numeroRequisicao;
	}

	public void setNumeroRequisicao(Integer numeroRequisicao) {
		this.numeroRequisicao = numeroRequisicao;
	}

	public StatusRequisicao getStatusRequisicao() {
		return statusRequisicao;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public void setStatusRequisicao(StatusRequisicao statusRequisicao) {
		this.statusRequisicao = statusRequisicao;
	}

	public void novaRequisicao() {
		this.statusRequisicao = StatusRequisicao.PENDENTE;
		GeraNumeroRequisicao gerarNumero = new GeraNumeroRequisicao();
		this.numeroRequisicao = gerarNumero.gerarNumeroRequisicao();
		this.dataCriacao = new Date();
	}

	public void statusAceito() {
		dataSaida = new Date();
		this.statusRequisicao = StatusRequisicao.EFETUADO;
	}

	public void statusRejeitado() {
		this.statusRequisicao = StatusRequisicao.RECUSADO;
	}

}
