package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.system.gestaoConstrucaoCivil.entity.AreaProduto;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.View.Summary;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class RequisicaoItem extends Item implements Serializable{

	@JsonView(Summary.class)
	@ManyToOne
	@JoinColumn(name = "id_area_produto")
	private AreaProduto areaProduto;
	
    @ManyToOne
	private Requisicao requisicao;
    
	public AreaProduto getAreaProduto() {
		return areaProduto;
	}

	public void setAreaProduto(AreaProduto areaProduto) {
		this.areaProduto = areaProduto;
	}

	public Requisicao getRequisicao() {
		return requisicao;
	}

	public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}

	
	
}
