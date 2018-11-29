package br.com.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.app.entity.CategoriaFinanceiro;
import br.com.app.enuns.CategoriaEnum;

@Repository
public interface CategoriaFinanceiroRepository extends JpaRepository<CategoriaFinanceiro,Long> {

	Page<CategoriaFinanceiro> findByCategoriaIsNull(Pageable page);

	Page<CategoriaFinanceiro> findByDescricaoContainingIgnoreCaseAndCategoriaIsNull(String descricao, Pageable page);

	//@EntityGraph(value = "CategoriaFinanceiro.detail", type = EntityGraphType.FETCH)
	Page<CategoriaFinanceiro> findByCategoriaNotNull(Pageable page);

	Page<CategoriaFinanceiro> findByDescricaoContainingIgnoreCaseAndCategoriaNotNull(String descricao, Pageable page);

	List<CategoriaFinanceiro> findByCategoriaId(Long id);

	//@EntityGraph(value = "CategoriaFinanceiro.detail", type = EntityGraphType.FETCH)
	Page<CategoriaFinanceiro> findAll(Pageable pageable);

	List<CategoriaFinanceiro> findByTipo(CategoriaEnum catEnum);

	List<CategoriaFinanceiro> findByTipoAndCategoriaIsNull(CategoriaEnum catEnum);

	Page<CategoriaFinanceiro> findByDescricaoContainingIgnoreCase(String descricao, Pageable page);

	boolean existsByDescricao(String descricao);

	CategoriaFinanceiro findByDescricao(String descricao);
	
}
