package br.com.system.gestaoConstrucaoCivil.entity.servicos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "servico_outros")
public class ServicoOutros extends ServicoEmpresa implements Serializable{

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
