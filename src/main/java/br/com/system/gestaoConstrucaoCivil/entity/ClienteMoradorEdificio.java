package br.com.system.gestaoConstrucaoCivil.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="cliente_morador_edificil")
public class ClienteMoradorEdificio extends ClienteMorador implements Serializable{

	@Column(nullable = false,length = 10)
    private Integer torre;
   
	@Column(nullable = false,length = 10)
    private Integer andar;
     
	@Column(nullable = false,length = 10)
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
