package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;

@Entity
@Table(name ="requisicao_outros", schema="almoxarifado")
public class RequisicaoOutros extends AbstractPersistable<Long> implements IRequisicao<RequisicaoOutrosItem>{

	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="id_informacao_requisicao",nullable = true)
	private InformacaoRequisicao informacaoRequisicao;
	
	@OneToMany(mappedBy = "requisicaoOutros", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<RequisicaoOutrosItem> itens;

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
		
		for(RequisicaoOutrosItem item: this.itens)
		{
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
