package br.com.app.financeiro.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.app.financeiro.domain.model.Cotacao;

public interface CotacaoRepository extends JpaRepository<Cotacao, Long>, JpaSpecificationExecutor<Cotacao>{


	Page<Cotacao> findByTemaIgnoreCaseContains(String descricao, Pageable page);

}
