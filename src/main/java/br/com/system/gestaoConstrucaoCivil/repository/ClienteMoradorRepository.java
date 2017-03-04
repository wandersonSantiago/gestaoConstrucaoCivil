package br.com.system.gestaoConstrucaoCivil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.system.gestaoConstrucaoCivil.entity.ClienteMorador;

public interface ClienteMoradorRepository extends JpaRepository<ClienteMorador, Long>{

	ClienteMorador findByCpf(String cpf);

}
