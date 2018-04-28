package br.com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.entity.Funcionario;



public interface FuncionarioRepository extends JpaRepository<Funcionario,Long>{

	List<Funcionario> findByCreaNotNull();
	
	
	
}
