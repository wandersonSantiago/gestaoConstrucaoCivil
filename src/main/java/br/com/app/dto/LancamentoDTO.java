package br.com.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import br.com.app.entity.Lancamento;
import br.com.app.entity.Usuario;
import br.com.app.enuns.CategoriaEnum;
import br.com.app.enuns.StatusLancamento;
import br.com.app.enuns.TipoLancamentoEnum;
import lombok.Data;

@Data
public class LancamentoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String descricao;
	private Date dataVencimento;
	private Date dataCadastro;
	private Date dataPagamentoOuRecebimento;
	private CategoriaEnum categoria;
	private TipoLancamentoEnum tipo;	
	private String observacao;
	private String objeto;
	private Long idObjeto;
	private StatusLancamento status;
	private boolean editavel;
	private BigDecimal valor = BigDecimal.ZERO;	
	private BigDecimal ValorEntrada = BigDecimal.ZERO;
	private BigDecimal valorSaida = BigDecimal.ZERO;
	private BigDecimal juros = BigDecimal.ZERO;
	private BigDecimal desconto = BigDecimal.ZERO;
	private BigDecimal total = BigDecimal.ZERO;
	private BigDecimal porcentagemJuros = BigDecimal.ZERO;
	private BigDecimal porcentagemDesconto = BigDecimal.ZERO;
	private Usuario usuario;
	private String comprovanteBase64;
	private String comprovanteBase64Path;
	
	public LancamentoDTO(Lancamento obj) {
		super();
		this.id = obj.getId();
		this.descricao = obj.getDescricao();
		this.dataVencimento = obj.getDataVencimento();
		this.dataCadastro = obj.getDataCadastro();
		this.dataPagamentoOuRecebimento = obj.getDataPagamentoOuRecebimento();
		this.categoria = obj.getCategoria();
		this.tipo = obj.getTipo();
		this.valor = obj.getValor();
		this.observacao = obj.getObservacao();
		this.status = obj.getStatus();
		this.editavel = dataPagamentoOuRecebimento == null;
		this.ValorEntrada = obj.getTipo() == TipoLancamentoEnum.CREDITO ? obj.getValor() : null;
		this.valorSaida = obj.getTipo() == TipoLancamentoEnum.DEBITO ? obj.getValor() : null;
		this.juros = Optional.ofNullable(obj.getJuros()).orElse(BigDecimal.ZERO);
		this.desconto = Optional.ofNullable(obj.getDesconto()).orElse(BigDecimal.ZERO);
		this.total = obj.getValorTotal();
		this.porcentagemDesconto = obj.getPorcentagemDesconto();
		this.porcentagemJuros = obj.getPorcentagemJuros();
		this.usuario = obj.getUsuario();
		this.comprovanteBase64 = obj.getComprovanteBase64();
		this.comprovanteBase64Path = obj.getComprovanteBase64Path();
	}


}
