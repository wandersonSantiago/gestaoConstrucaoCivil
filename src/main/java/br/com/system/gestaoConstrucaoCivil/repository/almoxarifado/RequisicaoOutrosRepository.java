package br.com.system.gestaoConstrucaoCivil.repository.almoxarifado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoOutros;

public interface RequisicaoOutrosRepository extends JpaRepository<RequisicaoOutros,Long> {

	@Query("FROM RequisicaoOutros requisicao WHERE requisicao.informacaoRequisicao.numeroRequisicao = ?1")
	RequisicaoOutros buscarPorNumeroRequisicao(Integer numeroRequisicao);
}
