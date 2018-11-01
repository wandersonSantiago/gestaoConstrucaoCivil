package br.com.app.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.app.enuns.CategoriaEnum;
import br.com.app.enuns.StatusLancamento;
import br.com.app.enuns.TipoLancamentoEnum;
import lombok.Data;

@Data
@Entity
@SequenceGenerator(name = "lancamento_id_seq", sequenceName = "lancamento_id_seq", allocationSize = 1, schema = "communs")
@Table(name = "lancamento", schema = "communs")
public class Lancamento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lancamento_id_seq")
	private Long id;
	
	@NotBlank(message="Descricao não pode ser em branco")
	private String descricao;
	
	@Column(name="comprovante_base_64", columnDefinition="text")
	private String comprovanteBase64;
	
	@NotNull(message="data de vencimento é obrigatória!")
	private Date dataVencimento;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAlteracao;
	
	private Date dataPagamentoOuRecebimento;
	
	@NotNull(message="Categoria é obrigatório!")
	@Enumerated(EnumType.STRING)
	private CategoriaEnum categoria;
	
	@NotNull(message="tipo de lançamento é obrigatória!")
	@Enumerated(EnumType.STRING)
	private TipoLancamentoEnum tipo;
	
	@ManyToOne
	@JoinColumn(name = "id_empreendimento", nullable = true)
	private Empreendimento empreendimento;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = true)
	private Usuario usuario;
	
	@NotNull(message="valor é obrigatório!")
	private BigDecimal valor = BigDecimal.ZERO;
	private BigDecimal juros = BigDecimal.ZERO;
	private BigDecimal desconto = BigDecimal.ZERO;
	private BigDecimal valorTotal = BigDecimal.ZERO;
		
	private String observacao;
	private String objeto;
	private Long idObjeto;
	
	@Transient
	private StatusLancamento status;
	
		
	public StatusLancamento getStatus() {
		if(dataPagamentoOuRecebimento == null) {
			if(dataVencimento.before(new Date())) {				
			return StatusLancamento.VENCIDO;
			}
			return StatusLancamento.PENDENTE;
		}
		if(tipo.equals(TipoLancamentoEnum.CREDITO)) {
			return StatusLancamento.RECEBIDO;
		}
			return StatusLancamento.PAGO;		
	}

	public BigDecimal somaValorTotal() {
		return Optional.ofNullable(valor).orElse(BigDecimal.ZERO)
						.subtract(Optional.ofNullable(desconto).orElse(BigDecimal.ZERO)).
						add(Optional.ofNullable(juros).orElse(BigDecimal.ZERO));
	}
	
	public BigDecimal getValorTotal() {
		this.valorTotal =  somaValorTotal();
		return this.valorTotal;
	}
	public BigDecimal getPorcentagemJuros() {
		return Optional.ofNullable(juros).orElse(BigDecimal.ZERO).
				divide(Optional.ofNullable(valor).orElse(BigDecimal.ZERO))
				.multiply(new BigDecimal(100));
	}
	
	public BigDecimal getPorcentagemDesconto() {
		return Optional.ofNullable(desconto).orElse(BigDecimal.ZERO).
				divide(Optional.ofNullable(valor).orElse(BigDecimal.ZERO))
				.multiply(new BigDecimal(100));
	}
	

	public String getComprovanteBase64Path() {
		return comprovanteBase64 = "data:image/jpeg;base64," + comprovanteBase64;
	}
}
