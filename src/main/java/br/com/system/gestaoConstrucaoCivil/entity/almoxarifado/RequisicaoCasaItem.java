package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.system.gestaoConstrucaoCivil.entity.AreaProduto;

@Entity
@Table(name = "requisicao_casa_item")
public class RequisicaoCasaItem extends Item implements Serializable {


	@Column(nullable = false)
	private Integer andar;
	
	@Column(nullable = false)
	private Integer casa;
 
	@ManyToOne
	@JoinColumn(name = "id_area_produto")
	private AreaProduto areaProduto;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_requisicao_casa")
	private RequisicaoCasa requisicaoCasa; 
	
	
	public Integer getAndar() {
		return andar;
	}

	public void setAndar(Integer andar) {
		this.andar = andar;
	}

	public Integer getCasa() {
		return casa;
	}

	public void setCasa(Integer casa) {
		this.casa = casa;
	}

	public RequisicaoCasa getRequisicaoCasa() {
		return requisicaoCasa;
	}

	public void setRequisicaoCasa(RequisicaoCasa requisicaoCasa) {
		this.requisicaoCasa = requisicaoCasa;
	}

	public AreaProduto getAreaProduto() {
		return areaProduto;
	}

	public void setAreaProduto(AreaProduto areaProduto) {
		this.areaProduto = areaProduto;
	}
	
}
