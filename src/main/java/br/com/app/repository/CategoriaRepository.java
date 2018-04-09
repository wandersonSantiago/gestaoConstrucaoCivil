package br.com.app.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.app.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

	
	@Query("FROM Categoria categoria WHERE categoria.tipoCategoria = PRODUTO" )
	Collection<Categoria> buscarPorCategoriaProduto();
	@Query("FROM Categoria categoria WHERE categoria.tipoCategoria = PACOTE_DE_SERVICO")
	Collection<Categoria> buscarPorCategoriaPacote();
}
