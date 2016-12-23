package br.com.system.gestaoConstrucaoCivil.repository.almoxarifado;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.CotacaoEmpresa;

public interface CotacaoEmpresaRepository extends JpaRepository<CotacaoEmpresa, Long> {

	@Query("FROM CotacaoEmpresa c WHERE c.cotacao.id = ?1")
	List<CotacaoEmpresa> buscarPorCotacao(Long idCotacoa);
	
	@Query("SELECT c FROM CotacaoEmpresa c JOIN c.itens i WHERE c.cotacao.id = ?1 AND i.status = 'GANHOU'")
	List<CotacaoEmpresa> buscarGanhadores(Long idCotacao);
	
}
