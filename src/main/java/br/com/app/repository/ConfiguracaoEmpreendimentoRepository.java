package br.com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.app.entity.ConfiguracaoEmpreendimento;

@Repository
public interface ConfiguracaoEmpreendimentoRepository extends JpaRepository<ConfiguracaoEmpreendimento,Long>{

	ConfiguracaoEmpreendimento findByEmpreendimentoId(Long id);



}
