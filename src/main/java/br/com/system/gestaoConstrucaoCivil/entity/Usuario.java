package br.com.system.gestaoConstrucaoCivil.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;


@Entity
@Table(name = "usuario")
public class Usuario extends AbstractPersistable<Long>{


	@Column(nullable = false,length = 50)
	private String nome;
	@Column(nullable = false,length = 40)
	private String email;
	@Column(nullable = false,length = 256)
	private String senha;
	@Column(nullable = false)
	private boolean ativo;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
}
