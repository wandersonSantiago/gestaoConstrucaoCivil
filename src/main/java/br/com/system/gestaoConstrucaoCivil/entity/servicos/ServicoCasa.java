package br.com.system.gestaoConstrucaoCivil.entity.servicos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "servico_casa")
public class ServicoCasa extends ServicoEmpresa{

	private Integer andar;
	private Integer numero;
	
	public Integer getAndar() {
		return andar;
	}
	public void setAndar(Integer andar) {
		this.andar = andar;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
}
