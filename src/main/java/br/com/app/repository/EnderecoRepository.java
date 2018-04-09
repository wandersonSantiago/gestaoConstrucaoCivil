package br.com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco,Long>{

}
