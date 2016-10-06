package br.com.system.gestaoConstrucaoCivil.entity.servicos;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "servico_empresa")
public class ServicoEmpresa extends AbstractPersistable<Long>{

	
	//@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@ManyToOne
	//OneToMany usar
	@JoinColumn(name="id_prestadora_servico",nullable = false)
	private PrestadoraServico prestadoraServico;
	
	
	private Double porcentagem;
	
	@ManyToOne
	//OneToMany
	@JoinColumn(name="id_pacote_servico",nullable = false)
	private PacoteServico pacoteServico;
	
	@ManyToOne
	@JoinColumn(name="id_empreendimento",nullable = false)
	private Empreendimento empreendimento;
	
	
	
	public PrestadoraServico getPrestadoraServico() {
		return prestadoraServico;
	}
	public void setPrestadoraServico(PrestadoraServico prestadoraServico) {
		this.prestadoraServico = prestadoraServico;
	}
	
	public Double getPorcentagem() {
		return porcentagem;
	}
	public void setPorcentagem(Double porcentagem) {
		this.porcentagem = porcentagem;
	}
	public PacoteServico getPacoteServico() {
		return pacoteServico;
	}
	public void setPacoteServico(PacoteServico pacoteServico) {
		this.pacoteServico = pacoteServico;
	}
	public Empreendimento getEmpreendimento() {
		return empreendimento;
	}
	public void setEmpreendimento(Empreendimento empreendimento) {
		this.empreendimento = empreendimento;
	}
	
	
}
