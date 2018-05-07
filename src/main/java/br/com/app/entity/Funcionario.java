package br.com.app.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "funcionario",  schema = "communs")
public class Funcionario  extends Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;
	@Column(nullable = false)
	private Integer carteiraTrabalho;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_admissao")
	private Date dataAdmissao;
	@ManyToOne
	@JoinColumn(name="id_empreendimento",nullable = true)
	private Empreendimento empreendimento;
	@Column(nullable = false)
	private boolean ativo;
	@ManyToOne
	@JoinColumn(name="id_cargo")
	private Cargo cargo;
	@Column(nullable = true )
	private Integer crea;
	
	
	
	public Integer getCarteiraTrabalho() {
		return carteiraTrabalho;
	    
	}
	public void setCarteiraTrabalho(Integer carteiraTrabalho) {
		this.carteiraTrabalho = carteiraTrabalho;
	}
	public Date  getDataAdmissao() {
		return dataAdmissao;
	}
	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	public Empreendimento getEmpreendimento() {
		return empreendimento;
	}
	public void setEmpreendimento(Empreendimento empreendimento) {
		this.empreendimento = empreendimento;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	public Integer getCrea() {
		return crea;
	}
	public void setCrea(Integer crea) {
		this.crea = crea;
	}
}
