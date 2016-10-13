package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "baixa_estoque_edificio")
public class BaixaEstoqueEdificio extends BaixaEstoque{

	private Integer andar;
	private Integer torre;
	private Integer apartamento;
	
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
	
	
}
