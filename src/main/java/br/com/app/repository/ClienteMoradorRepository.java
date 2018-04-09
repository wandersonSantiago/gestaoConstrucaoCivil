package br.com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.entity.ClienteMorador;

public interface ClienteMoradorRepository extends JpaRepository<ClienteMorador, Long>{

	ClienteMorador findByCpf(String cpf);

}
