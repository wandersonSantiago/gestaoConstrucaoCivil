 package br.com.system.gestaoConstrucaoCivil.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Email;
import org.springframework.data.jpa.domain.AbstractPersistable;

import br.com.system.gestaoConstrucaoCivil.enuns.PerfilUsuarioEnum;


@Entity
@Table(name = "usuario")
public class Usuario extends AbstractPersistable<Long>{

	@ManyToOne
	@JoinColumn(name="id_funcionario",nullable = true)
	private Funcionario funcionario;

	@ManyToOne
	@JoinColumn(name="id_empreendimento",nullable = true)
	private Empreendimento empreendimento;

	@ElementCollection(targetClass=PerfilUsuarioEnum.class,fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="perfil")
    @Column(name="perfil_usuario")
    List<PerfilUsuarioEnum> perfilsUsuario;
	
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
	public List<PerfilUsuarioEnum> getPerfilsUsuario() {
		return perfilsUsuario;
	}
	public void setPerfilsUsuario(List<PerfilUsuarioEnum> perfilsUsuario) {
		this.perfilsUsuario = perfilsUsuario;
	}
	
}
