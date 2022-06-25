package br.com.app.estoque.domain.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.app.estoque.domain.model.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

	@Query("FROM Transferencia  WHERE notaFiscal.numero = ?1")
	Transferencia buscarTransferenciaPorNumeroNota(Long numeroNota);
	
	@Query("FROM Transferencia WHERE empreendimentoDestino.id = ?1")
	Collection<Transferencia>  buscarTransferenciaRecebidas(Long idEmpreendimento);
	
	@Query("FROM Transferencia WHERE notaFiscal.empreendimento.id = ?1")
	Collection<Transferencia>  buscarTransferenciaEnviada(Long idEmpreendimento);

	@Query("FROM Transferencia trans WHERE trans.empreendimentoDestino.id = ?1")
	Page<Transferencia> buscarTransferenciaRecebidasComPaginacao(Long id, Pageable page);
	
	@Query("FROM Transferencia trans WHERE trans.notaFiscal.empreendimento.id = ?1")
	Page<Transferencia> buscarTransferenciaEnviadaComPaginacao(Long id, Pageable page);

	Page<Transferencia> findByNotaFiscalNumeroAndNotaFiscalEmpreendimentoId(Long long1, Long idEmpreendimento,
			Pageable page);

	Page<Transferencia> findByNotaFiscalEmpreendimentoId(Long idEmpreendimento, Pageable page);

	Page<Transferencia> findByEmpreendimentoDestinoId(Long idEmpreendimento, Pageable page);

	Page<Transferencia> findByNotaFiscalNumeroAndEmpreendimentoDestinoId(Long long1, Long idEmpreendimento,
			Pageable page);
	
	
}
