package br.com.system.gestaoConstrucaoCivil.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;


@Entity
@Table(name = "pacote_servico")
public class PacoteServico extends AbstractPersistable<Long>{

	
	@Column(nullable = false)
	private String codigoPacote;
	
	@Column(nullable = false,length = 50)
	private String descricao;
	
	@Column(nullable = false,length = 250)
	private String servicosAtribuidos;
	
	@Column(nullable = false,length = 250)
	private String criterioServico;
	
	@Column(nullable = false)
	private Double valor;
	
	
	
	
	public String getCodigoPacote() {
		return codigoPacote;
	}
	public void setCodigoPacote(String codigoPacote) {
		this.codigoPacote = codigoPacote;
	}
	public String getServicosAtribuidos() {
		return servicosAtribuidos;
	}
	public void setServicosAtribuidos(String servicosAtribuidos) {
		this.servicosAtribuidos = servicosAtribuidos;
	}
	public String getCriterioServico() {
		return criterioServico;
	}
	public void setCriterioServico(String criterioServico) {
		this.criterioServico = criterioServico;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
}
