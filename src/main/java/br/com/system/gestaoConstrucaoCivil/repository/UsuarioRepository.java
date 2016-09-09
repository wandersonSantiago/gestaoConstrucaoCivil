package br.com.system.gestaoConstrucaoCivil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.system.gestaoConstrucaoCivil.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
            
	Usuario findByNome(String nome);
	Usuario findByLogin(String login);
}
