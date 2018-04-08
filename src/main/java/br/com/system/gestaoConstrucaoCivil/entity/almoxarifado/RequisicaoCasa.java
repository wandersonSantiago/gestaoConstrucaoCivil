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
@Table(name ="requisicao_casa" , schema="almoxarifado")
public class RequisicaoCasa extends AbstractPersistable<Long> implements IRequisicao<RequisicaoCasaItem> {
		
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="id_informacao_requisicao",nullable = true)
	private InformacaoRequisicao informacaoRequisicao;

	 @OneToMany(mappedBy = "requisicaoCasa", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	 private List<RequisicaoCasaItem> itens;
	
	public InformacaoRequisicao getInformacaoRequisicao() {
		return informacaoRequisicao;
	}

	public void setInformacaoRequisicao(InformacaoRequisicao informacaoRequisicao) {
		this.informacaoRequisicao = informacaoRequisicao;
	}

	public List<RequisicaoCasaItem> getItens() {
		return itens;
	}

	public void setItens(List<RequisicaoCasaItem> itens) {
		this.itens = itens;
		
		for(RequisicaoCasaItem item: this.itens)
		{
			item.setRequisicaoCasa(this);
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
