package br.com.system.gestaoConstrucaoCivil.repository.almoxarifado;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.CotacaoEmpresa;

public interface CotacaoEmpresaRepository extends JpaRepository<CotacaoEmpresa, Long> {

	@Query("FROM CotacaoEmpresa cotacaoEmpresa WHERE cotacaoEmpresa.cotacao.id = ?1")
	List<CotacaoEmpresa> findbyId(Long idCotacao);
}
