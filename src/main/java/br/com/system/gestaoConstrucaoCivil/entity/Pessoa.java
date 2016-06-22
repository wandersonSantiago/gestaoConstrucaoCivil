package br.com.system.gestaoConstrucaoCivil.entity;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "pessoa")
public abstract class Pessoa extends AbstractPersistable<Long>{

	
	@Column(nullable = false,length = 50)
	protected String nomeCompleto;
	@Column(nullable = false)
	protected Integer idade;
	@ManyToOne
	@JoinColumn(name="id_endereco",nullable = false)
	protected Endereco endereco;
	@Column(nullable = false,length = 20)
	protected String rg;
	@Column(nullable = false,length = 20)
	protected String cpf;
	@Column(nullable = false,length = 15)
	protected String telefoneFixo;
	@Column(nullable = false,length = 15)
	protected String telefoneCelular;
	@Column(nullable = false)
	protected Date dataNascimento;
	
	
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefoneFixo() {
		return telefoneFixo;
	}
	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}
	public String getTelefoneCelular() {
		return telefoneCelular;
	}
	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
