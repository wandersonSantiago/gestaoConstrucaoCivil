package br.com.system.gestaoConstrucaoCivil.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "servico")
public class Servico extends AbstractPersistable<Long>{

	
	@ManyToOne
	@JoinColumn(name="id_prestadora_servico",nullable = false)
	private PrestadoraServico prestadoraServico;
	@ManyToOne
	@JoinColumn(name="id_imovel",nullable = false)
	private Imovel imovel;
	
	private Double porcentagem;
	@ManyToOne
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
	public Imovel getImovel() {
		return imovel;
	}
	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
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
