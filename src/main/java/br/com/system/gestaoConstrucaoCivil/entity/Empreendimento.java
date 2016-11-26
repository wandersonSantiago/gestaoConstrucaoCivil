package br.com.system.gestaoConstrucaoCivil.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.View;
import br.com.system.gestaoConstrucaoCivil.entity.servicos.PrestadoraServico;
import br.com.system.gestaoConstrucaoCivil.enuns.TipoEmpreendimentoEnum;
import br.com.system.gestaoConstrucaoCivil.util.data.LocalDateDeserializer;
import br.com.system.gestaoConstrucaoCivil.util.data.LocalDateSerializer;

@Entity
@Table(name = "empreendimento")
public class Empreendimento extends AbstractPersistable<Long> {


	@ManyToOne(cascade = {CascadeType.MERGE ,CascadeType.PERSIST})
	@JoinColumn(name="id_endereco_empreendimento",nullable = false)
	private Endereco enderecoEmpreendimento;
	
	@ManyToOne
	@JoinColumn(name="id_engenheiro_responsavel_funcionario",nullable = true)
	private Funcionario engenheiroResponsavelFuncionario;
	
	@ManyToOne
	@JoinColumn(name="id_engenheiro_responsavel_terceiro",nullable = true)
	private PrestadoraServico engenheiroResponsavelTerceiro;
	
	@Enumerated(EnumType.STRING)
	private TipoEmpreendimentoEnum tipoEmpreendimento;
    
	@JsonView(View.Summary.class)
	@Column(nullable = false,length = 50)
	private String descricao;
	
	
	@Column(nullable = false)
	private Double valorMaximoGastar;
	@Column(nullable = false)
	private Double valoresGastos = 0.0;
	@Column(nullable = false)
	private Double porcentagem =  0.0;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dataAbertura;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate datafechamento;
	
    @Column(nullable = false)
	private boolean ativo;
	
    public Endereco getEnderecoEmpreendimento() {
		return enderecoEmpreendimento;
	}
	public void setEnderecoEmpreendimento(Endereco enderecoEmpreendimento) {
		this.enderecoEmpreendimento = enderecoEmpreendimento;
	}
	public Funcionario getEngenheiroResponsavelFuncionario() {
		return engenheiroResponsavelFuncionario;
	}
	public void setEngenheiroResponsavelFuncionario(Funcionario engenheiroResponsavelFuncionario) {
		this.engenheiroResponsavelFuncionario = engenheiroResponsavelFuncionario;
	}
	public PrestadoraServico getEngenheiroResponsavelTerceiro() {
		return engenheiroResponsavelTerceiro;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setEngenheiroResponsavelTerceiro(PrestadoraServico engenheiroResponsavelTerceiro) {
		this.engenheiroResponsavelTerceiro = engenheiroResponsavelTerceiro;
	}

	public TipoEmpreendimentoEnum getTipoEmpreendimento() {
		return tipoEmpreendimento;
	}
	public void setTipoEmpreendimento(TipoEmpreendimentoEnum tipoEmpreendimento) {
		this.tipoEmpreendimento = tipoEmpreendimento;
	}
	public Double getValorMaximoGastar() {
		return valorMaximoGastar;
	}
	public void setValorMaximoGastar(Double valorMaximoGastar) {
		this.valorMaximoGastar = valorMaximoGastar;
	}
	
	public Double getValoresGastos() {
		return valoresGastos;
	}
	public void setValoresGastos(Double valoresGastos) {
		this.valoresGastos = valoresGastos;
	}
	public LocalDate getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public LocalDate getDatafechamento() {
		return datafechamento;
	}
	public void setDatafechamento(LocalDate datafechamento) {
		this.datafechamento = datafechamento;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public Double getPorcentagem() {
		return porcentagem;
	}
	public void setPorcentagem(Double porcentagem) {
		this.porcentagem = porcentagem;
	}

	
}
