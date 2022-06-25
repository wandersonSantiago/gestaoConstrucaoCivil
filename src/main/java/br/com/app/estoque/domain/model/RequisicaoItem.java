package br.com.app.estoque.domain.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "requisicao_item", schema = "almoxarifado")
public class RequisicaoItem extends Item implements Serializable {

	
	private static final long serialVersionUID = 3809274677351422452L;
	
	@ManyToOne
	@JoinColumn(name = "id_requisicao")
	private Requisicao requisicao;

	public Requisicao getRequisicao() {
		return requisicao;
	}

	public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}

	
	
}
