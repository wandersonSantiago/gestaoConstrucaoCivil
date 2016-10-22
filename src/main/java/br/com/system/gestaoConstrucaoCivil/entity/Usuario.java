 package br.com.system.gestaoConstrucaoCivil.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	
/*	@OneToMany(mappedBy = "", cascade = CascadeType.ALL)
	@Enumerated(EnumType.STRING)
	@JoinColumn(name="perfil_permissao",nullable = true)	
	private Enum<PerfilUsuarioEnum> perfil;*/
	
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
	private String email;
	@Column(nullable = false,length = 256)
	private String senha;
	@Column(nullable = false)
	private boolean ativo;
	private LocalDate dataCadastro;
	
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
	public LocalDate getDataCadastro() {
		return dataCadastro;
	} 
	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
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
