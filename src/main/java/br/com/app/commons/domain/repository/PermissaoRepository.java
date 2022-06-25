package br.com.app.commons.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.commons.domain.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long>{

}
