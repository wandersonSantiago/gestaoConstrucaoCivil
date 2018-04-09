package br.com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.entity.PermissaoUsuario;

public interface PermissaoUsuarioRepository extends JpaRepository<PermissaoUsuario, Long>{

	List<PermissaoUsuario> findByUsuario_id(Long id);
}
