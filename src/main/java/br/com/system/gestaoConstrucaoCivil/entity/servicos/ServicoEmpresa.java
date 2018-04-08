package br.com.system.gestaoConstrucaoCivil.entity.servicos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.Usuario;

@Entity
@SequenceGenerator(name = "servico_empresa_id_seq", sequenceName = "servico_empresa_id_seq", schema = "servicos", initialValue = 1, allocationSize = 1)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "servico_empresa", schema = "servicos")
public class ServicoEmpresa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "servico_empresa_id_seq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_prestadora_servico", nullable = false)
	private PrestadoraServico prestadoraServico;

	private Double porcentagem;
	private Date dataCadastro;
	private Date dataFechamento;
	private Date dataPagamento;
	private Date dataEncerramentoServico;
	private Double valorPacoteServico;
	private Double valorDesconto;
	private Double valorAdicional;
	private Double valorTotalPago;
	private String observacao;

	@ManyToOne
	@JoinColumn(name = "id_pacote_servico", nullable = false)
	private PacoteServico pacoteServico;

	@ManyToOne
	@JoinColumn(name = "id_empreendimento", nullable = false)
	private Empreendimento empreendimento;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuarioCadastro;

	@OneToMany(mappedBy = "servicoEmpresa", cascade = CascadeType.ALL)
	protected List<OcorrenciaServico> ocorrencias;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<OcorrenciaServico> getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(List<OcorrenciaServico> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Double getValorPacoteServico() {
		return valorPacoteServico;
	}

	public void setValorPacoteServico(Double valorPacoteServico) {
		this.valorPacoteServico = valorPacoteServico;
	}

	public Double getValorAdicional() {
		return valorAdicional;
	}

	public void setValorAdicional(Double valorAdicional) {
		this.valorAdicional = valorAdicional;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Double getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(Double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public Double getValorTotalPago() {
		return valorTotalPago;
	}

	public void setValorTotalPago(Double valorTotalPago) {
		this.valorTotalPago = valorTotalPago;
	}

	public Date getDataEncerramentoServico() {
		return dataEncerramentoServico;
	}

	public void setDataEncerramentoServico(Date dataEncerramentoServico) {
		this.dataEncerramentoServico = dataEncerramentoServico;
	}

}
