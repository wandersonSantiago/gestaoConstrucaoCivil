package br.com.system.gestaoConstrucaoCivil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.EstoqueEmpreendimento;

public interface EstoqueEmpreendimentoRepository extends JpaRepository<EstoqueEmpreendimento,Long>{

	 @Modifying
	 @Transactional(readOnly = false)
	 @Query("update EstoqueEmpreendimento set quantidade = (?1 + (SELECT e.quantidade FROM EstoqueEmpreendimento e WHERE e.id  = ?2)) WHERE id = ?2")
	 void adicionarQuantidadeEstoque(Integer quantidade,Long id);
		
	 @Modifying
	 @Transactional(readOnly = false)
	 @Query("update EstoqueEmpreendimento set quantidade = ((SELECT e.quantidade FROM EstoqueEmpreendimento e WHERE e.id  = ?2) - ?1) WHERE id = ?2")
	 void baixarEstoque(Integer quantidade,Long id);
	   
	    
	 @Query("SELECT CASE WHEN COUNT(id) > 0 THEN true ELSE false END FROM EstoqueEmpreendimento e WHERE e.id = :id")
	 boolean existeProduto(@Param("id") Long id);
}
