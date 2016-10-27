package br.com.system.gestaoConstrucaoCivil.repository.almoxarifado;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.EstoqueEmpreendimento;

public interface EstoqueEmpreendimentoRepository extends JpaRepository<EstoqueEmpreendimento, Long> {

	@Query("FROM EstoqueEmpreendimento estoque WHERE estoque.empreendimento.id = ?1")
	List<EstoqueEmpreendimento> buscarTodosPorEmpreendimento(Long id_empreendimento);

	@Query("SELECT e FROM EstoqueEmpreendimento e JOIN e.produto p  WHERE e.empreendimento.id  = ?1 AND p.id = ?2")
	EstoqueEmpreendimento estoque(Long id_empreendimento, Long id);

	@Query("SELECT CASE WHEN COUNT(e.id) > 0 THEN true ELSE false END FROM EstoqueEmpreendimento e JOIN e.produto p JOIN e.empreendimento empre WHERE p.id = :id AND empre.id = :idEmpreendimento")
	boolean existeProduto(@Param("id") Long id, @Param("idEmpreendimento") Long idEmpreendimento);

	@Query("FROM EstoqueEmpreendimento estoque JOIN estoque.produto produto WHERE CAST(produto.codigo as string) = :codigoOuCodigoBarra OR produto.codigoBarra = :codigoOuCodigoBarra")
	EstoqueEmpreendimento findByCodigoOrCodigoBarraEstoque(@Param("codigoOuCodigoBarra") String codigoOuCodigoBarra);

}
