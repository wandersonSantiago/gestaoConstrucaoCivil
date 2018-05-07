package br.com.app.entity.chamado;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "chamado_manutencao", schema = "chamado")
public class ChamadoManutencao extends Chamado {

	@Column(name = "descricao_servico")
	private String descricaoServico;

	public String getDescricaoServico() {
		return descricaoServico;
	}

	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}

}
