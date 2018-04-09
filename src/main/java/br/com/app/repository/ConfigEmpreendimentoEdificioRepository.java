package br.com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.app.entity.ConfigEmpreendimentoEdificio;

public interface ConfigEmpreendimentoEdificioRepository extends JpaRepository<ConfigEmpreendimentoEdificio,Long> {

	/*@Query("FROM ConfigEmpreendimentoEdificio c JOIN c.empreendimento e WHERE c.id =:#{#configEdificio.id}")
	ConfigEmpreendimentoEdificio findByEmpreendimento(@Param("configEdificio") ConfigEmpreendimentoEdificio configEdificio);*/
	
	@Query("FROM ConfigEmpreendimentoEdificio c JOIN c.empreendimento e WHERE e.id = ?1")
	ConfigEmpreendimentoEdificio findByEmpreendimentoId(Long idEmpreendimento);
	
}
