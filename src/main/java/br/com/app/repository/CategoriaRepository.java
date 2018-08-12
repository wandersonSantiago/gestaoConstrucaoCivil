package br.com.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.app.entity.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

	Page<Categoria> findByCategoriaIsNull(Pageable page);

	Page<Categoria> findByDescricaoContainingIgnoreCaseAndCategoriaIsNull(String descricao, Pageable page);

	@EntityGraph(value = "Categoria.detail", type = EntityGraphType.FETCH)
	Page<Categoria> findByCategoriaNotNull(Pageable page);

	Page<Categoria> findByDescricaoContainingIgnoreCaseAndCategoriaNotNull(String descricao, Pageable page);

	List<Categoria> findByCategoriaId(Long id);

	@EntityGraph(value = "Categoria.detail", type = EntityGraphType.FETCH)
	Page<Categoria> findAll(Pageable pageable);
	
}
