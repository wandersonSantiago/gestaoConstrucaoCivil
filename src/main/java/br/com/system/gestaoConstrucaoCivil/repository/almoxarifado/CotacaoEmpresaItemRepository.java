package br.com.system.gestaoConstrucaoCivil.repository.almoxarifado;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.CotacaoEmpresaItem;

public interface CotacaoEmpresaItemRepository extends JpaRepository<CotacaoEmpresaItem, Long> {

	@Query("FROM CotacaoEmpresaItem  i WHERE i.item.cotacao.id = ?1")
	List<CotacaoEmpresaItem> buscarItensPorIdCotacao(Long id);
}
