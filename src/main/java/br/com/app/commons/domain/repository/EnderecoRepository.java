package br.com.app.commons.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.commons.domain.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco,Long>{

}
