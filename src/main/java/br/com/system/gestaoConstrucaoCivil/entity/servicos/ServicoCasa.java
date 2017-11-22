package br.com.system.gestaoConstrucaoCivil.entity.servicos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "servico_casa" , schema="servicos")
public class ServicoCasa extends ServicoEmpresa{
	
	private static final long serialVersionUID = 1L;
	
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
