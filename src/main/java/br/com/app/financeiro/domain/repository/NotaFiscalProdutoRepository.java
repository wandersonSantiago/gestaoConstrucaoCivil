package br.com.app.financeiro.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import br.com.app.financeiro.domain.model.NotaFiscalProduto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NotaFiscalProdutoRepository extends JpaRepository<NotaFiscalProduto, Long> {

	@EntityGraph(value = "NotaFiscalProduto.detail", type = EntityGraphType.LOAD)
	@Query("FROM NotaFiscalProduto notaProduto  WHERE notaProduto.notaFiscal.empreendimento.id = ?1")
	List<NotaFiscalProduto> buscarNotaPorEmpreendimento(Long idEmpreendimento);

	@Query("FROM NotaFiscalProduto nota WHERE nota.notaFiscal.numero = ?1")
	NotaFiscalProduto buscarPorNumero(Long numero);

	Page<NotaFiscalProduto> findByNotaFiscalNumeroAndNotaFiscalEmpreendimentoId(Long descricao, Long idEmpreendimento,
			Pageable page);
	
	@EntityGraph(value = "NotaFiscalProduto.detail", type = EntityGraphType.FETCH)
	Page<NotaFiscalProduto> findAll(Pageable pageable);
}
