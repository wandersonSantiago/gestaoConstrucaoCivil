package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.system.gestaoConstrucaoCivil.entity.AreaProduto;

@Entity
@Table(name = "requisicao_outros_item")
public class RequisicaoOutrosItem   extends Item implements Serializable{

	private String descricao;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_requisicao_outros")
	private RequisicaoOutros requisicaoOutros;
	
	@ManyToOne
	@JoinColumn(name = "id_area_produto")
	private AreaProduto areaProduto;
	
	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
}

	public RequisicaoOutros getRequisicaoOutros() {
		return requisicaoOutros;
	}

	public void setRequisicaoOutros(RequisicaoOutros requisicaoOutros) {
		this.requisicaoOutros = requisicaoOutros;
	}

	public AreaProduto getAreaProduto() {
		return areaProduto;
	}

	public void setAreaProduto(AreaProduto areaProduto) {
		this.areaProduto = areaProduto;
	}
	
	
	
}
