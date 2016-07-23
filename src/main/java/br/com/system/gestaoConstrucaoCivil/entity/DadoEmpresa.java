package br.com.system.gestaoConstrucaoCivil.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "dado_empresa")
public class DadoEmpresa extends AbstractPersistable<Long>  {

	
	@Column(nullable = false,length = 50)
	private String razaoSocial;
	@Column(nullable = false,length = 50)
	private String nomeFantasia;
	@Column(nullable = false,length = 20)
	private String cnpj;
	@Column(nullable = false,length = 20)
	private String inscricaoEstadual;
	@OneToOne
    @JoinColumn(name="id_endereco",nullable = false)
	private Endereco endereco;
	@Column(nullable = false,length = 15)
	private String telefone;
	@Column(length = 40)
	private String email;
	
	
	
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}
	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
