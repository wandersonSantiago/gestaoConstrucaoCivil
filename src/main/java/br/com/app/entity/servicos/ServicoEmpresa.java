package br.com.app.entity.servicos;

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

import br.com.app.entity.Empreendimento;
import br.com.app.entity.Estrutura;
import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "servico_empresa_id_seq", sequenceName = "servico_empresa_id_seq", allocationSize = 1, schema = "servicos")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "servico_empresa", schema = "servicos")
public class ServicoEmpresa implements Serializable{

	private static final long serialVersionUID = 5629005997737363720L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "servico_empresa_id_seq")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_prestadora_servico", nullable = false)
	private PrestadoraServico prestadoraServico;

	private Double porcentagem;
	private Date dataPrevisaoTermino;
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
	@JoinColumn(name = "id_estrutura")
	private Estrutura estrutura;

	@OneToMany(mappedBy = "servicoEmpresa", cascade = CascadeType.ALL)
	private List<OcorrenciaServico> ocorrencias;

}
