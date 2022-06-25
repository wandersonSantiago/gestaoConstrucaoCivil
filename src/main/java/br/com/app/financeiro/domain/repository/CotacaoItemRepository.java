package br.com.app.financeiro.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.app.financeiro.domain.model.CotacaoItem;

public interface CotacaoItemRepository extends JpaRepository<CotacaoItem, Long>, JpaSpecificationExecutor<CotacaoItem>{

}
