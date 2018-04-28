package br.com.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.app.enuns.EstadoCivilEnum;

@Entity
@SequenceGenerator(name = "pessoa_id_seq", sequenceName = "pessoa_id_seq",allocationSize = 1,schema="communs")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pessoa" , schema = "communs")
public abstract class Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_id_seq")
	private Long id;
	
	@Column(nullable = false,length = 50)
	protected String nomeCompleto;
	@Column(nullable = false)
	protected Integer idade;
	@OneToOne(cascade = {CascadeType.MERGE ,CascadeType.PERSIST})
	@JoinColumn(name="id_endereco",nullable = false)
	protected Endereco endereco;
	
	@Column(nullable = false,length = 20,unique = true)
	protected String rg;
	@Column(nullable = false,length = 20,unique = true)
	protected String cpf;
	@Column(nullable = false,length = 15)
	protected String telefoneFixo;
	@Column(nullable = false,length = 15)
	protected String telefoneCelular;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento")
	protected Date dataNascimento;
	@Column(nullable = false)
	protected String sexo;
	@Column(nullable = true)
	@Enumerated(EnumType.STRING)
	protected EstadoCivilEnum estadoCivil;
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public EstadoCivilEnum getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(EstadoCivilEnum estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	
}
