package br.com.app.repository.almoxarifado;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.app.entity.almoxarifado.NotaFiscalProduto;

public interface NotaFiscalProdutoRepository extends JpaRepository<NotaFiscalProduto,Long> {

	@EntityGraph(value = "NotaFiscalProduto.detail", type = EntityGraphType.LOAD)
    @Query("FROM NotaFiscalProduto notaProduto  WHERE notaProduto.notaFiscal.empreendimento.id = ?1")
	public List<NotaFiscalProduto> buscarNotaPorEmpreendimento(Long idEmpreendimento);
	
	@Query("FROM NotaFiscalProduto nota WHERE nota.notaFiscal.numero = ?1")
	public NotaFiscalProduto buscarPorNumero(Long numero);

	public Page<NotaFiscalProduto> findByNotaFiscalNumeroAndNotaFiscalEmpreendimentoId(Long descricao,
			Long idEmpreendimento, Pageable page);
}
