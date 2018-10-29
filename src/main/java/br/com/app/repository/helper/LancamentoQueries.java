package br.com.app.repository.helper;

import org.springframework.data.domain.Page;

import br.com.app.entity.Lancamento;
import br.com.app.repository.filter.LancamentoFilter;

public interface LancamentoQueries {
	
	 Page<Lancamento> filters(LancamentoFilter filter);

}
