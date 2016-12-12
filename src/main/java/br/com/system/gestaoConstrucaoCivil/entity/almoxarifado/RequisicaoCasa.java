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



@Entity
@Table(name ="requisicao_casa")
public class RequisicaoCasa extends AbstractPersistable<Long> implements IRequisicao<RequisicaoCasaItem>{
	
	
	   
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
	}
    
	
	
}
