package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "requisicao_outros")
public class RequisicaoOutros  extends Requisicao{

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
