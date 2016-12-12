package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.View.Summary;
import br.com.system.gestaoConstrucaoCivil.enuns.StatusRequisicao;
import br.com.system.gestaoConstrucaoCivil.util.GeraCodigo;


@Entity
@SequenceGenerator(name = "informacaoRequisicao_id_seq",
sequenceName = "informacaoRequisicao_id_seq",
initialValue = 1,
allocationSize = 1)
@Table(name = "informacao_requisicao")
public class InformacaoRequisicao extends AbstractPersistable<Long> implements Serializable{

	
	@JsonView(Summary.class)
	@Column(name = "numero_requisicao")
	private Integer numeroRequisicao;

	private Date dataSaida;
	
	@JsonView(Summary.class)
    @Enumerated(EnumType.STRING)
	private StatusRequisicao statusRequisicao;
	
    @ManyToOne
    @JoinColumn(name="id_empreendimento")
    private Empreendimento empreendimento;
   	
   
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
	public Empreendimento getEmpreendimento() {
		return empreendimento;
	}
	public void setEmpreendimento(Empreendimento empreendimento) {
		this.empreendimento = empreendimento;
	}
	public void novaRequisicao()
	{
		this.statusRequisicao = StatusRequisicao.PENDENTE;
        GeraCodigo gerar = new GeraCodigo(100000,999999);
		this.numeroRequisicao = gerar.gerarNumeroRequisicao();
	}
	public void statusAceito()
	{
		dataSaida = new Date();
		this.statusRequisicao = StatusRequisicao.EFETUADO;
	}
	public void statusRejeitado()
	{
		this.statusRequisicao = StatusRequisicao.RECUSADO;
	}
}
