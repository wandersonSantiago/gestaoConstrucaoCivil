package br.com.app.entity.almoxarifado;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.app.entity.Empreendimento;
import br.com.app.pojo.InformacaoEntradaProduto;
import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "estoque_empreendimento_id_seq", sequenceName = "estoque_empreendimento_id_seq", allocationSize = 1, schema = "almoxarifado")
@NamedEntityGraph(name = "EstoqueEmpreendimento.detail", attributeNodes = { @NamedAttributeNode("produto"),
		@NamedAttributeNode("empreendimento") })
@Table(name = "estoque_empreendimento", schema = "almoxarifado")
public class EstoqueEmpreendimento {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estoque_empreendimento_id_seq")
	private Long id;

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

	public boolean isNegativo() {
		return quantidade >= 0;
	}

}
