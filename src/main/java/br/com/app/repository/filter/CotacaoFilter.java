package br.com.app.repository.filter;

import java.util.Date;

import br.com.app.enuns.StatusCotacao;
import lombok.Data;

@Data
public class CotacaoFilter {

	private String tema;
	private StatusCotacao status;
	private Date dataCadastroDe;
	private Date dataCadastroAte;
	private Date dataLimiteDe;
	private Date dataLimiteAte;
	private Date dataFechamentoDe;
	private Date dataFechamentoAte;
	private double valorMinimo;
	private double valorMaximo;
	
}
