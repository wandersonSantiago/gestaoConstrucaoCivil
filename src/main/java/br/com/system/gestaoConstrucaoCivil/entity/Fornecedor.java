package br.com.system.gestaoConstrucaoCivil.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "fornecedor")
public class Fornecedor extends AbstractPersistable<Long> {

	@Column(nullable = false, length = 50)
	private String contato;
	@Column(nullable = true, length = 50)
	private String observacao;
	@ManyToOne
	@JoinColumn(name = "id_dado_empresa", nullable = false)
	private DadoEmpresa dadosEmpresa;
	@Column(nullable = false)
	private boolean ativo;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "produtos_fornecedores", joinColumns = @JoinColumn(name = "id_fornecedor"), 
	inverseJoinColumns = @JoinColumn(name = "id_produto"))
	private List<Produto> produtos;

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public DadoEmpresa getDadosEmpresa() {
		return dadosEmpresa;
	}

	public void setDadosEmpresa(DadoEmpresa dadosEmpresa) {
		this.dadosEmpresa = dadosEmpresa;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
