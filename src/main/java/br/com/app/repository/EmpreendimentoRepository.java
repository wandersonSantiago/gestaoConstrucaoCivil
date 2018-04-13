package br.com.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.app.entity.Empreendimento;

@Repository
public interface EmpreendimentoRepository extends JpaRepository<Empreendimento,Long>{


	Page<Empreendimento> findByDescricaoContainsIgnoreCase(String descricao, Pageable page);

	Page<Empreendimento> findByMatrizIsNull(Pageable pageRequest);

	Page<Empreendimento> findByMatriz_id(Long id, Pageable pageRequest);

	Page<Empreendimento> findByDescricaoContainsIgnoreCaseAndMatrizIsNull(String descricao, Pageable page);

	Page<Empreendimento> findByMatriz_idAndDescricaoContainsIgnoreCase(Long id, String descricao, Pageable page);

}
