package br.com.system.gestaoConstrucaoCivil.repository.almoxarifado;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Transferencia;

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
}
