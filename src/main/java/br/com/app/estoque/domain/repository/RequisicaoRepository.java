package br.com.app.estoque.domain.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.app.estoque.domain.model.Requisicao;

public interface RequisicaoRepository extends JpaRepository<Requisicao, Long>{

	@Query("FROM Requisicao requisicao WHERE requisicao.numeroRequisicao = ?1")
	Requisicao findByNumeroRequisicao(Integer numeroRequisicao);
	
	@Query("FROM Requisicao requisicao WHERE requisicao.empreendimento.id = ?1")
	Collection<Requisicao> findByEmpreendimento(Long idEmpreendimento);

	@Query("FROM Requisicao requisicao WHERE requisicao.empreendimento.id = ?1")
	Page<Requisicao> buscarTodasRequisicoesComPaginacao(Long id, Pageable pages);

	Page<Requisicao> findByNumeroRequisicaoAndEmpreendimentoIdAndIdUsuario(Integer numero, Long idEmpreendimento, Long idUsuario, Pageable page);

	Page<Requisicao> findByNumeroRequisicaoAndEmpreendimentoId(Integer numero, Long idEmpreendimento, Pageable page);

	Requisicao findByIdAndEmpreendimentoId(Long idRequisicao, Long idEmpreendimento);
}
