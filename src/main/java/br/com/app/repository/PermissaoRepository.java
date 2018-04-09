package br.com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.entity.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long>{

}
