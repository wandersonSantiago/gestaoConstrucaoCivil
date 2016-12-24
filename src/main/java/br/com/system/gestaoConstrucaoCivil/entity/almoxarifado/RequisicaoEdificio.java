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
@Table(name ="requisicao_edificio")
public class RequisicaoEdificio extends AbstractPersistable<Long> implements IRequisicao<RequisicaoEdificioItem>{

	

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="id_informacao_requisicao",nullable = true)
	private InformacaoRequisicao informacaoRequisicao;
    
    @OneToMany(mappedBy = "requisicaoEdificio", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<RequisicaoEdificioItem> itens;
	
	
    public List<RequisicaoEdificioItem> getItens() {
		return itens;
	}

	public void setItens(List<RequisicaoEdificioItem> itens) {
	
		this.itens = itens;
		
		for(RequisicaoEdificioItem item: this.itens)
		{
			item.setRequisicaoEdificio(this);
		}
	}

	public InformacaoRequisicao getInformacaoRequisicao() {
		return informacaoRequisicao;

	}

	public void setInformacaoRequisicao(InformacaoRequisicao informacaoRequisicao) {
		this.informacaoRequisicao = informacaoRequisicao;
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
