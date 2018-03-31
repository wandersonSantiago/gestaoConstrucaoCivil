package br.com.system.gestaoConstrucaoCivil.repository.servicos;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.system.gestaoConstrucaoCivil.entity.servicos.ServicoEdificio;

public interface ServicoEdificioRepository  extends JpaRepository<ServicoEdificio, Long>{

	Collection<ServicoEdificio> findByTorreAndAndarAndApartamento(Integer torre, Integer andar, Integer apartamento);

}
