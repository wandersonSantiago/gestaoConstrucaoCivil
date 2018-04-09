package br.com.app.entity.almoxarifado;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.app.entity.Empreendimento;

@Entity
@SequenceGenerator(name = "requisicao_outros_seq", sequenceName = "requisicao_outros_seq", schema = "almoxarifado", initialValue = 1, allocationSize = 1)
@Table(name = "requisicao_outros", schema = "almoxarifado")
public class RequisicaoOutros implements IRequisicao<RequisicaoOutrosItem> {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "requisicao_outros_seq")
	private Long id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_informacao_requisicao", nullable = true)
	private InformacaoRequisicao informacaoRequisicao;

	@OneToMany(mappedBy = "requisicaoOutros", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<RequisicaoOutrosItem> itens;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public InformacaoRequisicao getInformacaoRequisicao() {
		return informacaoRequisicao;
	}

	public void setInformacaoRequisicao(InformacaoRequisicao informacaoRequisicao) {
		this.informacaoRequisicao = informacaoRequisicao;
	}

	public List<RequisicaoOutrosItem> getItens() {
		return itens;
	}

	public void setItens(List<RequisicaoOutrosItem> itens) {
		this.itens = itens;

		for (RequisicaoOutrosItem item : this.itens) {
			item.setRequisicaoOutros(this);
		}
	}

	@Override
	public Empreendimento empreendimentoSaida() {

		return informacaoRequisicao.getEmpreendimento();
	}

	@Override
	public Empreendimento empreendimentoEntrada() {

		return informacaoRequisicao.getEmpreendimento();
	}

}
