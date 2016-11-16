package br.com.system.gestaoConstrucaoCivil.repository.almoxarifado;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

	@Query("FROM Transferencia transf WHERE transf.notaFiscal.numero = ?1")
	Transferencia buscarTransferenciaPorNumeroNota(Long numeroNota);
	
	@Query("FROM Transferencia WHERE empreendimentoDestino.id = ?1")
	Collection<Transferencia>  buscarTransferenciaRecebidas(Long idEmpreendimento);
	
	@Query("FROM Transferencia WHERE notaFiscal.empreendimento.id = ?1")
	Collection<Transferencia>  buscarTransferenciaEnviada(Long idEmpreendimento);
}
