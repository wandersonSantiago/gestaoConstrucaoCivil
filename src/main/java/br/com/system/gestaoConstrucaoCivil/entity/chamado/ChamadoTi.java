package br.com.system.gestaoConstrucaoCivil.entity.chamado;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import br.com.system.gestaoConstrucaoCivil.enuns.chamado.TipoEquipamentoChamadoTi;

@Entity
@Table(name="chamado_ti", schema="chamado")
public class ChamadoTi extends Chamado implements Serializable {


	private static final long serialVersionUID = 1L;

	@Column(name="descricao_servico")
	private String descricaoServico;
	
	@Enumerated(EnumType.STRING)
	private TipoEquipamentoChamadoTi tipoEquipamento;
	
	
	
	public String getDescricaoServico() {
		return descricaoServico;
	}

	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}

	public TipoEquipamentoChamadoTi getTipoEquipamento() {
		return tipoEquipamento;
	}

	public void setTipoEquipamento(TipoEquipamentoChamadoTi tipoEquipamento) {
		this.tipoEquipamento = tipoEquipamento;
	}
	
	
	
}
