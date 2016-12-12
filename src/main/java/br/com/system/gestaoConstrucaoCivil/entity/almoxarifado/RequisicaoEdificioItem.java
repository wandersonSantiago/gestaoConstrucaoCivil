package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.system.gestaoConstrucaoCivil.entity.AreaProduto;

@Entity
@Table(name = "requisicao_edificio_item")
public class RequisicaoEdificioItem extends Item implements Serializable{

	
	private Integer andar;
	private Integer torre;
	private Integer apartamento;
	
	@ManyToOne
	@JoinColumn(name = "id_area_produto")
	private AreaProduto areaProduto;
	
	
	@ManyToOne
	@JoinColumn(name = "id_requisicao_edificio")
	private RequisicaoEdificio requisicaoEdificio;
	
	
	
	public Integer getAndar() {
		return andar;
	}
	public void setAndar(Integer andar) {
		this.andar = andar;
	}
	public Integer getTorre() {
		return torre;
	}
	public void setTorre(Integer torre) {
		this.torre = torre;
	}
	public Integer getApartamento() {
		return apartamento;
	}
	public void setApartamento(Integer apartamento) {
		this.apartamento = apartamento;
	}
	public RequisicaoEdificio getRequisicaoEdificio() {
		return requisicaoEdificio;
	}
	public void setRequisicaoEdificio(RequisicaoEdificio requisicaoEdificio) {
		this.requisicaoEdificio = requisicaoEdificio;
	}
	public AreaProduto getAreaProduto() {
		return areaProduto;
	}
	public void setAreaProduto(AreaProduto areaProduto) {
		this.areaProduto = areaProduto;
	}
	
	
	
}
