package br.com.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.app.entity.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

	Page<Categoria> findByCategoriaIsNull(Pageable page);

	Page<Categoria> findByDescricaoContainingIgnoreCaseAndCategoriaIsNull(String descricao, Pageable page);

	
	
}
