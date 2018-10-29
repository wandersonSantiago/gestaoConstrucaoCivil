package br.com.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.app.entity.Lancamento;
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
	private BigDecimal valor;	
	private String observacao;
	private String objeto;
	private Long idObjeto;
	private StatusLancamento status;
	
	
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
	}

 
}
