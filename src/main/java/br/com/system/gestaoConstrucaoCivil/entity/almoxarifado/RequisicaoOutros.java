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

import br.com.system.gestaoConstrucaoCivil.pojo.CriaListaEntradaOuBaixa;
import br.com.system.gestaoConstrucaoCivil.pojo.EntradaOuBaixa;

@Entity
@Table(name ="requisicao_outros")
public class RequisicaoOutros extends AbstractPersistable<Long> implements IRequisicao<RequisicaoOutrosItem>{

	
	
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
	public List<EntradaOuBaixa> itens() {

		CriaListaEntradaOuBaixa<RequisicaoOutrosItem> criar = new CriaListaEntradaOuBaixa<RequisicaoOutrosItem>();
		return criar.criarListaDeEntradaOuBaixa(itens, informacaoRequisicao.getEmpreendimento());
	}
}
