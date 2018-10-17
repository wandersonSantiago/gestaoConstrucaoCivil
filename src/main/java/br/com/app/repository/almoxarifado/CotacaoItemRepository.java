package br.com.app.repository.almoxarifado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import br.com.app.entity.almoxarifado.CotacaoItem;

public interface CotacaoItemRepository extends JpaRepository<CotacaoItem, Long>, JpaSpecificationExecutor<CotacaoItem>, QuerydslPredicateExecutor<CotacaoItem>{

}
