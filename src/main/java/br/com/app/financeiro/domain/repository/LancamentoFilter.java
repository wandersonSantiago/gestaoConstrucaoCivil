package br.com.app.financeiro.domain.repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.app.estoque.domain.enuns.CategoriaEnum;
import br.com.app.financeiro.domain.enuns.TipoLancamentoEnum;
import br.com.app.infraestructure.util.Page;
import lombok.Data;

@Data
public class LancamentoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String descricao;
	private Date dataVendimentoDe;
	private Date dataVendimentoAte;
	private Date dataPagamentoOuRecebimentoDe;
	private Date dataPagamentoOuRecebimentoAte;
	private Date dataCadastroDe;
	private Date dataCadastroAte;
	private CategoriaEnum categoria;
	private TipoLancamentoEnum tipo;
	private BigDecimal valorDe;
	private BigDecimal valorAte;
	private String observação;
	private String objeto;
	private Long idObjeto;

	private String adicional;

	private Page page;
}
