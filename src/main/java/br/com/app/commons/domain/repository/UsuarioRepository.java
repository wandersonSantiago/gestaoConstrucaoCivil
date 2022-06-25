package br.com.app.commons.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
 
import org.springframework.data.repository.query.Param;

import br.com.app.commons.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
            
	Usuario findByNome(String nome);
	@EntityGraph(value = "Usuario.detail", type = EntityGraphType.FETCH)
	Usuario findByLogin(String login);
	
	@Query("SELECT CASE WHEN COUNT(login) > 0 THEN true ELSE false END FROM Usuario u WHERE u.login = :login")
	boolean existeLogin(@Param("login") String login);
	
	Page<Usuario> findByEmpreendimentoMatriz_id(Long id, Pageable page);
	
	Page<Usuario> findByEmpreendimento_id(Long id, Pageable page);
	
	Page<Usuario> findByNomeContainsIgnoreCase(String descricao, Pageable page);
	
	Page<Usuario> findByNomeContainsIgnoreCaseAndEmpreendimento_matriz_id(String descricao, Long id, Pageable page);
	
	Page<Usuario> findByNomeContainsIgnoreCaseAndEmpreendimento_id(String descricao, Long id, Pageable page);

	Usuario findByIdAndEmpreendimentoId(Long id,Long idEmpreendimento);
	
	@EntityGraph(value = "Usuario.detail", type = EntityGraphType.FETCH)
	Page<Usuario> findAll(Pageable pageable);
}
