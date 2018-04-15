package br.com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.entity.Morador;

public interface MoradorRepository extends JpaRepository<Morador, Long>{

	Morador findByCpf(String cpf);

}
