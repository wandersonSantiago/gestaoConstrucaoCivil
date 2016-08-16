package br.com.system.gestaoConstrucaoCivil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;

public interface EmpreendimentoRepository extends JpaRepository<Empreendimento,Long>{

	@Query("FROM Empreendimento e WHERE e.id NOT IN(SELECT c.empreendimento FROM ConfigEmpreendimento c)")
	List<Empreendimento> buscaEmpreendimentoSemConfiguracao();
}
