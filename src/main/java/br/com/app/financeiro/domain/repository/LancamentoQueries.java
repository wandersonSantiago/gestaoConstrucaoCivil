package br.com.app.financeiro.domain.repository;

import org.springframework.data.domain.Page;

import br.com.app.financeiro.domain.model.Lancamento;

public interface LancamentoQueries {
	
	 Page<Lancamento> filters(LancamentoFilter filter);

}
