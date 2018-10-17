package br.com.app.repository.filter;

import java.util.Date;

import javax.persistence.OneToOne;

import br.com.app.enuns.StatusCotacao;
import br.com.app.pojo.Page;
import lombok.Getter;
import lombok.Setter;



public class CotacaoFilter {

	@Getter @Setter
	private String tema;
	@Getter @Setter
	private String descricaoItem;
	@Getter @Setter
	private StatusCotacao status;
	@Getter @Setter
	private Date dataCadastroDe;
	@Getter @Setter
	private Date dataCadastroAte;
	@Getter @Setter
	private Date dataLimiteDe;
	@Getter @Setter
	private Date dataLimiteAte;
	@Getter @Setter
	private Date dataFechamentoDe;
	@Getter @Setter
	private Date dataFechamentoAte;
	
	@Getter @Setter
	@OneToOne
	private Page page;
	
	
}
