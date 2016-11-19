package br.com.system.gestaoConstrucaoCivil.repository.almoxarifado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Requisicao;

public interface RequisicaoRepository extends JpaRepository<Requisicao, Long>{

	@Query("SELECT CASE WHEN COUNT(numeroRequisicao) > 0 THEN true ELSE false END FROM Requisicao r WHERE r.numeroRequisicao = :numeroRequisicao")
	boolean existeNnumeroRequisicao(@Param("numeroRequisicao") Integer numeroRequisicao);
}
