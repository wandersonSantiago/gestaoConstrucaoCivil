package br.com.app.commons.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.commons.domain.model.Fabricante;

public interface FabricanteRepository extends JpaRepository<Fabricante, Long> {

	Boolean existsByDadoEmpresaCnpj(String cnpj);

	Optional<Fabricante> findByDadoEmpresaCnpj(String cnpj);

	Page<Fabricante> findByDadoEmpresaCnpjContaining(String descricao, Pageable page);

	Page<Fabricante> findByDadoEmpresaRazaoSocialIgnoreCaseContaining(String descricao, Pageable page);


}
