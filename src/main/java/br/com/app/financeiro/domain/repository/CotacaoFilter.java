package br.com.app.financeiro.domain.repository;

import java.util.Date;

import br.com.app.financeiro.domain.enuns.StatusCotacao;
import br.com.app.infraestructure.util.Page;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CotacaoFilter {

	private String tema;
	private String descricaoItem;
	private StatusCotacao status;
	private Date dataCadastroDe;
	private Date dataCadastroAte;
	private Date dataLimiteDe;
	private Date dataLimiteAte;
	private Date dataFechamentoDe;
	private Date dataFechamentoAte;

	private Page page;

}
