package br.com.app.repository.almoxarifado;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.app.entity.almoxarifado.RequisicaoEdificio;

public interface RequisicaoEdificioRepository extends JpaRepository<RequisicaoEdificio,Long>{

	@Query("FROM RequisicaoEdificio requisicao WHERE requisicao.informacaoRequisicao.numeroRequisicao = ?1")
	RequisicaoEdificio buscarPorNumeroRequisicao(Integer numeroRequisicao);
	
	@Query("FROM RequisicaoEdificio requisicao WHERE requisicao.informacaoRequisicao.empreendimento.id = ?1")
	List<RequisicaoEdificio>  buscarTodasRequisicoes( Long empreendimento);

	@Query("FROM RequisicaoEdificio requisicao WHERE requisicao.informacaoRequisicao.empreendimento.id = ?1")
	Page<RequisicaoEdificio> buscarTodasRequisicoesComPaginacao(Long id, Pageable pages);
}
