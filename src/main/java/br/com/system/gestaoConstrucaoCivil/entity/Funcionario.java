package br.com.system.gestaoConstrucaoCivil.entity;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.system.gestaoConstrucaoCivil.util.data.LocalDateDeserializer;
import br.com.system.gestaoConstrucaoCivil.util.data.LocalDateSerializer;

@Entity
@Table(name = "funcionario")
public class Funcionario  extends Pessoa implements Serializable{


	@Column(nullable = false)
	private Integer carteiraTrabalho;
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dataAdmissao;
	
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
	public LocalDate  getDataAdmissao() {
		return dataAdmissao;
	}
	public void setDataAdmissao(LocalDate dataAdmissao) {
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
