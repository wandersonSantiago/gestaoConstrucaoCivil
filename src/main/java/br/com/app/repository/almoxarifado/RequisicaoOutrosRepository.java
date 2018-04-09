package br.com.app.repository.almoxarifado;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.app.entity.almoxarifado.RequisicaoOutros;

public interface RequisicaoOutrosRepository extends JpaRepository<RequisicaoOutros,Long> {

	@Query("FROM RequisicaoOutros requisicao WHERE requisicao.informacaoRequisicao.numeroRequisicao = ?1")
	RequisicaoOutros buscarPorNumeroRequisicao(Integer numeroRequisicao);
	
	@Query("FROM RequisicaoOutros requisicao WHERE requisicao.informacaoRequisicao.empreendimento.id = ?1")
	List<RequisicaoOutros>  buscarTodasRequisicoes( Long empreendimento);

	@Query("FROM RequisicaoOutros requisicao WHERE requisicao.informacaoRequisicao.empreendimento.id = ?1")
	Page<RequisicaoOutros> buscarTodasRequisicoesComPaginacao(Long id, Pageable pages);
}
