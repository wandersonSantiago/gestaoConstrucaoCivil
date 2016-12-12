package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.pojo.InformacaoEntradaProduto;

@Entity
@Table(name = "estoque_empreendimento")
public class EstoqueEmpreendimento implements Serializable {

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(nullable = false)
	private Integer quantidade;

	@Column(nullable = true)
	private String localizacao;

	@OneToOne
	@JoinColumn(name = "id_empreendimento", nullable = true)
	private Empreendimento empreendimento;

	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;

	@Column(nullable = false)
	private Integer quantidadeMaxima = 100;

	@Column(nullable = false)
	private Integer quantidadeMinima = 1;

	@Transient
	private Double custoMedio;
	@Transient
	private Double valorTotal;

	public void setInforProduto(InformacaoEntradaProduto inforProduto) {

		custoMedio = (inforProduto.getValorTotal() / inforProduto.getQuantidadeTotalNota());
		valorTotal = (inforProduto.getValorTotal() / inforProduto.getQuantidadeTotalNota()) * getQuantidade();

		if (custoMedio.isNaN()) {
			custoMedio = 0.0;
		}
	}

	public Double getCustoMedio() {

		return custoMedio;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

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

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public boolean isNegativo() {
		if (quantidade >= 0) {
			return true;
		}
		return false;
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
