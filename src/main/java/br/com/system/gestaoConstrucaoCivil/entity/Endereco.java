package br.com.system.gestaoConstrucaoCivil.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "endereco")
public class Endereco extends AbstractPersistable<Long>  {

	@Column(nullable = false,length = 50)
	private String rua;
	@Column(nullable = false,length = 50)
	private String bairro;
	@Column(nullable = false,length = 50)
	private String cidade;
	@Column(nullable = false)
	private Integer numero;
	@Column(nullable = false)
	private String estado;
	@Column(nullable = false)
	private String cep;
	
	
	
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}

}
