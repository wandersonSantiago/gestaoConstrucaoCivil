package br.com.app.repository.almoxarifado;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.app.entity.almoxarifado.RequisicaoCasa;

public interface RequisicaoCasaRepository extends JpaRepository<RequisicaoCasa,Long> {

	@Query("FROM RequisicaoCasa requisicao WHERE requisicao.informacaoRequisicao.numeroRequisicao = ?1")
	RequisicaoCasa buscarPorNumeroRequisicao(Integer numeroRequisicao);
	
	@Query("FROM RequisicaoCasa requisicao WHERE requisicao.informacaoRequisicao.empreendimento.id = ?1")
	List<RequisicaoCasa>  buscarTodasRequisicoes( Long empreendimento);

	@Query("FROM RequisicaoCasa requisicao WHERE requisicao.informacaoRequisicao.empreendimento.id = ?1")
	Page<RequisicaoCasa> buscarTodasRequisicoesComPaginacao(Long id, Pageable pages);
}
