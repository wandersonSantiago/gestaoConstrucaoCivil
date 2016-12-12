package br.com.system.gestaoConstrucaoCivil.repository.almoxarifado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoCasa;

public interface RequisicaoCasaRepository extends JpaRepository<RequisicaoCasa,Long> {

	@Query("FROM RequisicaoCasa requisicao WHERE requisicao.informacaoRequisicao.numeroRequisicao = ?1")
	RequisicaoCasa buscarPorNumeroRequisicao(Integer numeroRequisicao);
}
