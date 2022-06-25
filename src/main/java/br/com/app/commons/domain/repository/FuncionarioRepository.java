package br.com.app.commons.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.commons.domain.model.Funcionario;



public interface FuncionarioRepository extends JpaRepository<Funcionario,Long>{

	List<Funcionario> findByCreaNotNull();
	
	
	
}
