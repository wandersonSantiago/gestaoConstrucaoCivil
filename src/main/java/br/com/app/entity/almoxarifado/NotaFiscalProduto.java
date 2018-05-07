package br.com.app.entity.almoxarifado;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.app.entity.Empreendimento;
import br.com.app.entity.almoxarifado.interfaces.EntradaOuBaixa;
import lombok.Data;

@Data
@Entity
@NamedEntityGraph(name = "NotaFiscalProduto.detail", attributeNodes = { @NamedAttributeNode("notaFiscal"),
		@NamedAttributeNode("fornecedor") })

@SequenceGenerator(name = "nota_fiscal_produto_id_seq", sequenceName = "nota_fiscal_produto_id_seq", allocationSize = 1, schema = "almoxarifado")
@Table(name = "nota_fiscal_produto", schema = "almoxarifado")
public class NotaFiscalProduto implements EntradaOuBaixa<NotaFiscalItem> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nota_fiscal_produto_id_seq")
	private Long id;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "id_nota_fiscal", nullable = true)
	private NotaFiscal notaFiscal;

	@ManyToOne
	@JoinColumn(name = "id_fornecedor", nullable = false)
	private Fornecedor fornecedor;

	@OneToMany(mappedBy = "notaFiscalProduto", cascade = CascadeType.ALL)
	@Fetch(FetchMode.SUBSELECT)
	private List<NotaFiscalItem> itens;

	public List<NotaFiscalItem> getItens() {
		return itens;
	}

	public void setItens(List<NotaFiscalItem> itens) {

		this.itens = itens;
		itens.forEach(item -> {
			item.setNotaFiscalProduto(this);
		});
	}

	@Override
	public Empreendimento empreendimentoSaida() {

		return getNotaFiscal().getEmpreendimento();
	}

	@Override
	public Empreendimento empreendimentoEntrada() {

		return getNotaFiscal().getEmpreendimento();
	}

	public void novaNotaProduto() {
		getNotaFiscal().novaNota();
	}

}
