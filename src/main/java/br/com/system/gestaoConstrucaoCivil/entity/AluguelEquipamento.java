package br.com.system.gestaoConstrucaoCivil.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "aluguel_equipamento", schema = "communs")
public class AluguelEquipamento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String nomeEquipamento;
	private Double precoLocacao;
	private Double valorAquisicao;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeEquipamento() {
		return nomeEquipamento;
	}
	public void setNomeEquipamento(String nomeEquipamento) {
		this.nomeEquipamento = nomeEquipamento;
	}
	public Double getPrecoLocacao() {
		return precoLocacao;
	}
	public void setPrecoLocacao(Double precoLocacao) {
		this.precoLocacao = precoLocacao;
	}
	public Double getValorAquisicao() {
		return valorAquisicao;
	}
	public void setValorAquisicao(Double valorAquisicao) {
		this.valorAquisicao = valorAquisicao;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AluguelEquipamento other = (AluguelEquipamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
