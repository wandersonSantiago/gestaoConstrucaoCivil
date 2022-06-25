package br.com.app.estoque.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.app.estoque.domain.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Produto findByCodigo(Integer codigo);

	Produto findByCodigoBarra(String codigoBarra);

	@Query("FROM Produto prod WHERE prod.ativo = ?1")
	List<Produto> buscarTodosPorStatus(Boolean status);

	@Query("SELECT CASE WHEN COUNT(codigo) > 0 THEN true ELSE false END FROM Produto p WHERE p.codigo = :codigo")
	boolean existeCodigo(@Param("codigo") Integer codigo);

	@Query("SELECT CASE WHEN COUNT(codigoBarra) > 0 THEN true ELSE false END FROM Produto p WHERE p.codigoBarra = :codigoBarra")
	boolean existeCodigoBarra(@Param("codigoBarra") String codigoBarra);

	@Query("FROM Produto produto WHERE CAST(produto.codigo as string) = :codigoOuCodigoBarra OR produto.codigoBarra = :codigoOuCodigoBarra")
	Produto findByCodigoOrCodigoBarra(@Param("codigoOuCodigoBarra") String codigoOuCodigoBarra);

	@EntityGraph(value = "Produto.detail", type = EntityGraphType.LOAD)
	@Query("FROM Produto produto")
	List<Produto> buscarTodos();

	Page<Produto> findByCodigo(String descricao, Pageable page);

	Page<Produto> findByDescricaoIgnoreCaseContaining(String descricao, Pageable page);
	
	@EntityGraph(value = "Produto.detail", type = EntityGraphType.FETCH)
	Page<Produto> findAll(Pageable page); 
}
