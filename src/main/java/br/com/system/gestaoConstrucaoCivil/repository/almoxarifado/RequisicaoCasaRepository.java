package br.com.system.gestaoConstrucaoCivil.repository.almoxarifado;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoCasa;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoEdificio;

public interface RequisicaoCasaRepository extends JpaRepository<RequisicaoCasa,Long> {

	@Query("FROM RequisicaoCasa requisicao WHERE requisicao.informacaoRequisicao.numeroRequisicao = ?1")
	RequisicaoCasa buscarPorNumeroRequisicao(Integer numeroRequisicao);
	
	@Query("FROM RequisicaoCasa requisicao WHERE requisicao.informacaoRequisicao.empreendimento.id = ?1")
	List<RequisicaoCasa>  buscarTodasRequisicoes( Long empreendimento);
}
