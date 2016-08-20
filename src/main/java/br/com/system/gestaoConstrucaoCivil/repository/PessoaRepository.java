package br.com.system.gestaoConstrucaoCivil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.system.gestaoConstrucaoCivil.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa,Long>{

	@Query("SELECT CASE WHEN COUNT(rg) > 0 THEN true ELSE false END FROM Pessoa p WHERE p.rg = :rg")
	boolean existRg(@Param("rg") String rg);
	
	
	@Query("SELECT CASE WHEN COUNT(cpf) > 0 THEN true ELSE false END FROM Pessoa p WHERE p.cpf = :cpf")
	boolean existCpf(@Param("cpf") String cpf);
}
