package br.com.app.commons.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.commons.domain.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

	Optional<Fornecedor> findByDadoEmpresaCnpj(String cnpj);

	Page<Fornecedor> findByDadoEmpresaCnpjContaining(String descricao, Pageable page);

	Page<Fornecedor> findByDadoEmpresaRazaoSocialIgnoreCaseContaining(String descricao, Pageable page);

	boolean existsByDadoEmpresaCnpj(String cnpj);

}
