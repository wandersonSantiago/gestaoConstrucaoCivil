package br.com.app.repository.almoxarifado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import br.com.app.entity.almoxarifado.Cotacao;

public interface CotacaoRepository extends JpaRepository<Cotacao, Long>, JpaSpecificationExecutor<Cotacao>, QuerydslPredicateExecutor<Cotacao>{


	Page<Cotacao> findByTemaIgnoreCaseContains(String descricao, Pageable page);

}
