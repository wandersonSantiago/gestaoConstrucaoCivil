package br.com.system.gestaoConstrucaoCivil.repository.almoxarifado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

	@Query("FROM Transferencia transf WHERE transf.notaFiscal.numero = ?1")
	Transferencia buscarTransferenciaPorNumeroNota(Long numeroNota);
}
