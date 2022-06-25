package br.com.app.commons.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.commons.domain.model.Morador;

public interface MoradorRepository extends JpaRepository<Morador, Long>{

	Morador findByCpf(String cpf);

}
