package br.com.system.gestaoConstrucaoCivil.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;


@Entity
@Table(name = "prestadora_servico")
public class PrestadoraServico extends AbstractPersistable<Long>{

	@OneToOne(cascade = {CascadeType.MERGE ,CascadeType.PERSIST})
	@JoinColumn(name="id_dado_empresa",nullable = false)
	private DadoEmpresa dadoEmpresa;
	
	@OneToMany(mappedBy = "prestadoraServico", cascade = CascadeType.ALL)
	private List<ServicoEmpresa> servicos;
	
	public DadoEmpresa getDadoEmpresa() {
		return dadoEmpresa;
	}
	public void setDadoEmpresa(DadoEmpresa dadoEmpresa) {
		this.dadoEmpresa = dadoEmpresa;
	}
	
}
