package br.com.app.repository.almoxarifado;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.app.entity.almoxarifado.Requisicao;

public interface RequisicaoRepository extends JpaRepository<Requisicao, Long>{

	@Query("FROM Requisicao requisicao WHERE requisicao.numeroRequisicao = ?1")
	Requisicao buscarPorNumeroRequisicao(Integer numeroRequisicao);
	
	/*@Query("FROM RequisicaoEdificio requisicao WHERE requisicao..empreendimento.id = ?1")
	Collection<Requisicao>  findByEmpreendimento(Long empreendimento);*/

	/*@Query("FROM RequisicaoEdificio requisicao WHERE requisicao.informacaoRequisicao.empreendimento.id = ?1")
	Page<Requisicao> buscarTodasRequisicoesComPaginacao(Long id, Pageable pages);*/
}
