package br.com.app.repository;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import br.com.app.entity.Lancamento;
import br.com.app.enuns.TipoLancamentoEnum;

public interface LancamentoRepository extends JpaRepository<Lancamento,Long>, JpaSpecificationExecutor<Lancamento>, QuerydslPredicateExecutor<Lancamento>{

	@Query("SELECT SUM(valor) FROM Lancamento l where l.tipo = ?1 AND l.dataPagamentoOuRecebimento != null")
	BigDecimal somaSaldoDataInicioEDataFinal(TipoLancamentoEnum tipo);
	
	@Query("SELECT SUM(valor) FROM Lancamento l where l.tipo = ?1 AND l.dataVencimento >= ?2 AND l.dataVencimento <= ?3 AND l.dataPagamentoOuRecebimento = null")
	BigDecimal somaSaldoDataInicioEDataFinalNaoPagoOuNaoRecebido(TipoLancamentoEnum tipo, Date dataInicial, Date dataFinal);

	@Query("SELECT SUM(valor) FROM Lancamento l where l.tipo = ?1 AND l.dataVencimento >= ?2 AND l.dataVencimento <= ?3 AND l.dataPagamentoOuRecebimento != null")
	BigDecimal somaSaldoDataInicioEDataFinalPagoOuRecebido(TipoLancamentoEnum tipo, Date dataInicial, Date dataFinal);


}
