package br.com.system.gestaoConstrucaoCivil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.system.gestaoConstrucaoCivil.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa,Long>{

}
