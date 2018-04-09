 package br.com.system.gestaoConstrucaoCivil.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Email;


@Entity
@SequenceGenerator(name = "usuario_id_seq", sequenceName = "usuario_id_seq",schema="communs")
@Table(name = "usuario" , schema = "communs")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_id_seq")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_funcionario",nullable = true)
	private Funcionario funcionario;

	@ManyToOne
	@JoinColumn(name="id_empreendimento",nullable = true)
	private Empreendimento empreendimento;
	
	@Column(nullable = false,length = 50)
	private String nome;
	@Column(nullable = false,length = 15,unique = false)
	private String login;
	@Column(nullable = true,length = 40)
	@Email
	private String email;
	@Column(nullable = false,length = 256)
	private String senha;
	@Column(nullable = false)
	private boolean ativo;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_cadastro")
	private Date dataCadastro;
	
	public Usuario()
	{
		dataCadastro = new Date();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	} 
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public Empreendimento getEmpreendimento() {
		return empreendimento;
	}
	public void setEmpreendimento(Empreendimento empreendimento) {
		this.empreendimento = empreendimento;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	
}
