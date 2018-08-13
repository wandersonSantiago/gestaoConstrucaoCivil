package br.com.app.repository.servicos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.entity.servicos.PacoteServico;

public interface PacoteServicoRepository extends JpaRepository<PacoteServico,Long>{

	Page<PacoteServico> findByCodigo(String descricao, Pageable page);

	Page<PacoteServico> findByDescricaoIgnoreCaseContaining(String descricao, Pageable page);

}
