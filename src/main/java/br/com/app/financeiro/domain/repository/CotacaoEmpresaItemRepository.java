package br.com.app.financeiro.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.app.financeiro.domain.model.CotacaoEmpresaItem;

public interface CotacaoEmpresaItemRepository extends JpaRepository<CotacaoEmpresaItem, Long> {

	@Query("FROM CotacaoEmpresaItem  i WHERE i.cotacaoEmpresa.cotacao.id = ?1")
	List<CotacaoEmpresaItem> buscarItensPorIdCotacao(Long id);
}
