package br.com.system.gestaoConstrucaoCivil.repository.servicos;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.system.gestaoConstrucaoCivil.entity.servicos.ServicoOutros;

public interface ServicoOutrosRepository extends JpaRepository<ServicoOutros,Long> {

	Collection<ServicoOutros> findByDescricao(String outros);

}
