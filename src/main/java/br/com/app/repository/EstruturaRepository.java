package br.com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.app.entity.Estrutura;

@Repository 
public interface EstruturaRepository extends JpaRepository<Estrutura, Long>{


	Optional<Estrutura> findByIdAndIdEmpreendimento(Long id, Long id2);

	Page<Estrutura> findByDescricaoContainsIgnoreCaseAndIdEmpreendimento(String descricao, Long id, Pageable page);

	Page<Estrutura> findByIdEmpreendimento(Long id, Pageable page);

	List<Estrutura> findByRaizIdAndIdEmpreendimento(Long id, Long id2);

	List<Estrutura> findByRaizIsNullAndIdEmpreendimento(Long id);

}
