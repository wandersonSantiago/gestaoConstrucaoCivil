package br.com.system.gestaoConstrucaoCivil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.system.gestaoConstrucaoCivil.entity.Funcionario;



public interface FuncionarioRepository extends JpaRepository<Funcionario,Long>{

	List<Funcionario> findByCreaNotNull();
	
	
	
}
