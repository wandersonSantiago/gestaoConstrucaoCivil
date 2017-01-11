package br.com.system.gestaoConstrucaoCivil.repository.almoxarifado;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoEdificio;

public interface RequisicaoEdificioRepository extends JpaRepository<RequisicaoEdificio,Long>{

	@Query("FROM RequisicaoEdificio requisicao WHERE requisicao.informacaoRequisicao.numeroRequisicao = ?1")
	RequisicaoEdificio buscarPorNumeroRequisicao(Integer numeroRequisicao);
	
	@Query("FROM RequisicaoEdificio requisicao WHERE requisicao.informacaoRequisicao.empreendimento.id = ?1")
	List<RequisicaoEdificio>  buscarTodasRequisicoes( Long empreendimento);
}
