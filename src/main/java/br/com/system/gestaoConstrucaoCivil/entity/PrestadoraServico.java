package br.com.system.gestaoConstrucaoCivil.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;


@Entity
@Table(name = "prestadora_servico")
public class PrestadoraServico extends AbstractPersistable<Long>{

	@ManyToOne
	@JoinColumn(name="id_dado_empresa",nullable = false)
	private DadoEmpresa dadoEmpresa;
	
	
	public DadoEmpresa getDadoEmpresa() {
		return dadoEmpresa;
	}
	public void setDadoEmpresa(DadoEmpresa dadoEmpresa) {
		this.dadoEmpresa = dadoEmpresa;
	}
	
}
