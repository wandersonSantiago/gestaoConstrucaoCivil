package br.com.app.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import br.com.app.entity.Lancamento;
import br.com.app.enuns.TipoLancamentoEnum;

public interface LancamentoRepository extends JpaRepository<Lancamento,Long>, JpaSpecificationExecutor<Lancamento>, QuerydslPredicateExecutor<Lancamento>{

	@Query("SELECT SUM(valorTotal) FROM Lancamento l where l.tipo = ?1 AND l.dataPagamentoOuRecebimento != null AND l.empreendimento.id = ?2")
	BigDecimal somaSaldoDataInicioEDataFinal(TipoLancamentoEnum tipo, Long idEmpreendimento);
	
	@Query("SELECT SUM(valorTotal) FROM Lancamento l where l.tipo = ?1 AND l.dataVencimento >= ?2 AND l.dataVencimento <= ?3 AND l.dataPagamentoOuRecebimento = null AND l.empreendimento.id = ?4")
	BigDecimal somaSaldoDataInicioEDataFinalNaoPagoOuNaoRecebido(TipoLancamentoEnum tipo, Date dataInicial, Date dataFinal, Long idEmpreendimento);

	@Query("SELECT SUM(valorTotal) FROM Lancamento l where l.tipo = ?1 AND l.dataVencimento >= ?2 AND l.dataVencimento <= ?3 AND l.dataPagamentoOuRecebimento != null AND l.empreendimento.id = ?4")
	BigDecimal somaSaldoDataInicioEDataFinalPagoOuRecebido(TipoLancamentoEnum tipo, Date dataInicial, Date dataFinal, Long idEmpreendimento);

	@Query("SELECT SUM(valorTotal) FROM Lancamento l where l.tipo = ?1 AND l.dataVencimento >= ?2 AND l.dataPagamentoOuRecebimento = null AND l.empreendimento.id = ?3")
	BigDecimal somaSaldoAreceberOuAPagar(TipoLancamentoEnum tipo, Date dataInicial, Long idEmpreendimento);

	@Query("SELECT SUM(valorTotal) FROM Lancamento l where l.empreendimento.id = ?1 AND l.dataVencimento < ?2 AND l.dataPagamentoOuRecebimento = null AND  l.tipo = ?3")
	BigDecimal somaSaldoVencidaAPagar(Long id, Date date, TipoLancamentoEnum debito);

	@Query("SELECT SUM(valorTotal) FROM Lancamento l where l.empreendimento.id = ?1 AND l.dataVencimento < ?2 AND l.dataPagamentoOuRecebimento = null AND  l.tipo = ?3")
	BigDecimal somaSaldoVencidaAReceber(Long id, Date date, TipoLancamentoEnum credito);

	Page<Lancamento> findByTipoAndDataVencimentoBetweenAndEmpreendimentoIdAndDataPagamentoOuRecebimentoIsNotNull(
			TipoLancamentoEnum credito, Date dataInicial, Date dataFinal, Long id, Pageable page);

	Page<Lancamento> findByTipoAndDataVencimentoBeforeAndEmpreendimentoIdAndDataPagamentoOuRecebimentoIsNull(
			TipoLancamentoEnum debito, Date date, Long id, Pageable pageable);

	Page<Lancamento> findByTipoAndDataVencimentoBetweenAndEmpreendimentoIdAndDataPagamentoOuRecebimentoIsNull(
			TipoLancamentoEnum debito, Date dataInicial, Date dataFinal, Long id, Pageable pageable);

	List<Lancamento> findByEmpreendimento_idAndDataPagamentoOuRecebimentoIsNullAndDataVencimentoBefore(Long idEmpreendimento, Date hoje );

	@Query("SELECT l FROM Lancamento l where l.empreendimento.id = ?1 AND l.dataVencimento < ?2 AND l.dataPagamentoOuRecebimento = null ")
	List<Lancamento> findByEmpreendimento_idAndDataVencimentoBefore(Long id, Date date);


}
