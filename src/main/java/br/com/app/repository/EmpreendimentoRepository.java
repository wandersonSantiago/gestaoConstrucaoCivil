package br.com.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.app.entity.Empreendimento;

@Repository
public interface EmpreendimentoRepository extends JpaRepository<Empreendimento,Long>{


	Page<Empreendimento> findByDescricaoContainsIgnoreCase(String descricao, Pageable page);

}
