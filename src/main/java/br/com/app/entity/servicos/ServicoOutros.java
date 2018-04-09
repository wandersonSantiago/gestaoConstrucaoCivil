package br.com.app.entity.servicos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "servico_outros" , schema="servicos")
public class ServicoOutros extends ServicoEmpresa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
