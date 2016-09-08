package br.com.system.gestaoConstrucaoCivil.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.AbstractPersistable;

import ch.qos.logback.core.joran.spi.DefaultClass;


@Entity
@Table(name = "estoque_empreendimento")
public class EstoqueEmpreendimento implements Serializable{
 
	
	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(nullable = false)
	private Integer quantidade;
	
    @Column(nullable = true)
	private String localizacao;
	
	@OneToOne
	@JoinColumn(name="id_empreendimento",nullable = true)
	private Empreendimento empreendimento;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "empreendimento_protudo", joinColumns = @JoinColumn(name = "id_produto"), 
	inverseJoinColumns = @JoinColumn(name = "id_empreendimento"))
	private List<Produto>  produto;
	

	@Column(nullable = false)
	private Integer quantidadeMaxima = 100;
	
	@Column(nullable = false)
	private Integer quantidadeMinima = 1;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Empreendimento getEmpreendimento() {
		return empreendimento;
	}
	public void setEmpreendimento(Empreendimento empreendimento) {
		this.empreendimento = empreendimento;
	}
	
	public List<Produto> getProduto() {
		return produto;
	}	
	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
	
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	
	public Integer getQuantidadeMaxima() {
		return quantidadeMaxima;
	}
	public void setQuantidadeMaxima(Integer quantidadeMaxima) {
		this.quantidadeMaxima = quantidadeMaxima;
	}
	public Integer getQuantidadeMinima() {
		return quantidadeMinima;
	}
	public void setQuantidadeMinima(Integer quantidadeMinima) {
		this.quantidadeMinima = quantidadeMinima;
	}
	public void adicionarProduto(Produto prod)
	{
	      if(produto == null)
	      {
	    	  produto = new ArrayList<Produto>();
	      }
	      produto.add(prod);
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
		EstoqueEmpreendimento other = (EstoqueEmpreendimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
	
}
