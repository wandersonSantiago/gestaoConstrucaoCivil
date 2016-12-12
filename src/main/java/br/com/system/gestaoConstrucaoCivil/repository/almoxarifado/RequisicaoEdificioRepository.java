package br.com.system.gestaoConstrucaoCivil.repository.almoxarifado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoEdificio;

public interface RequisicaoEdificioRepository extends JpaRepository<RequisicaoEdificio,Long>{

	@Query("FROM RequisicaoEdificio requisicao WHERE requisicao.informacaoRequisicao.numeroRequisicao = ?1")
	RequisicaoEdificio buscarPorNumeroRequisicao(Integer numeroRequisicao);
}
