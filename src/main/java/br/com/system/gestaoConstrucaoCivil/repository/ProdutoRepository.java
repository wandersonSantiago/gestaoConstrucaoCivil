package br.com.system.gestaoConstrucaoCivil.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.system.gestaoConstrucaoCivil.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Long>{

 Produto findByCodigo(Integer codigo);
 Produto findByCodigoBarra(String codigoBarra);
 
 @Query("SELECT CASE WHEN COUNT(codigo) > 0 THEN true ELSE false END FROM Produto p WHERE p.codigo = :codigo")
 boolean existeCodigo(@Param("codigo") Integer codigo);
 

 @Query("SELECT CASE WHEN COUNT(codigoBarra) > 0 THEN true ELSE false END FROM Produto p WHERE p.codigoBarra = :codigoBarra")
 boolean existeCodigoBarra(@Param("codigoBarra") String codigoBarra);
}
