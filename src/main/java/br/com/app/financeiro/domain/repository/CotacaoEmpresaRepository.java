package br.com.app.financeiro.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.app.financeiro.domain.model.CotacaoEmpresa;

public interface CotacaoEmpresaRepository extends JpaRepository<CotacaoEmpresa, Long> {

	@Query("SELECT c FROM CotacaoEmpresa c WHERE c.cotacao.id = ?1")
	List<CotacaoEmpresa> buscarPorCotacao(Long idCotacoa);
	
	@Query("SELECT c FROM CotacaoEmpresa c WHERE c.cotacao.id = ?1 AND c.ganhou = true")
	List<CotacaoEmpresa> buscarGanhadores(Long idCotacao);
	
}
