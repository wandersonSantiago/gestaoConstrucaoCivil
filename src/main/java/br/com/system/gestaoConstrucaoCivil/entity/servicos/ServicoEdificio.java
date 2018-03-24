package br.com.system.gestaoConstrucaoCivil.entity.servicos;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "servico_edificio" , schema="servicos")
public class ServicoEdificio extends ServicoEmpresa{

	private static final long serialVersionUID = 1L;
	
	private Integer torre;
	private Integer andar;
	private Integer apartamento;
	
	public Integer getTorre() {
		return torre;
	}
	public void setTorre(Integer torre) {
		this.torre = torre;
	}
	public Integer getAndar() {
		return andar;
	}
	public void setAndar(Integer andar) {
		this.andar = andar;
	}
	public Integer getApartamento() {
		return apartamento;
	}
	public void setApartamento(Integer apartamento) {
		this.apartamento = apartamento;
	}
}
