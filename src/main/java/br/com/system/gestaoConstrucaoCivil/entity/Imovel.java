package br.com.system.gestaoConstrucaoCivil.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;


@Entity
@Table(name = "imovel")
public class Imovel extends AbstractPersistable<Long> {

	
	@Column(nullable = true)
	private Integer torre;
	@Column(nullable = true)
	private Integer andar;
	
	
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

}
