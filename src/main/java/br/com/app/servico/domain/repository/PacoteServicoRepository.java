package br.com.app.servico.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.servico.domain.model.PacoteServico;

public interface PacoteServicoRepository extends JpaRepository<PacoteServico,Long>{

	Page<PacoteServico> findByCodigo(String descricao, Pageable page);

	Page<PacoteServico> findByDescricaoIgnoreCaseContaining(String descricao, Pageable page);

}
