package br.com.system.gestaoConstrucaoCivil.repository.almoxarifado;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoCasa;

public interface RequisicaoCasaRepository extends JpaRepository<RequisicaoCasa, Long>{
	RequisicaoCasa findByNumeroRequisicao(Integer numeroRequisicao);
}
