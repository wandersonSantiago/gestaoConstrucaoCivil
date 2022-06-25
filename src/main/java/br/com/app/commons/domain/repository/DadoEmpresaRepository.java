package br.com.app.commons.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.app.commons.domain.model.DadoEmpresa;

public interface DadoEmpresaRepository extends JpaRepository<DadoEmpresa, Long> {

	@Query("SELECT CASE WHEN COUNT(cnpj) > 0 THEN true ELSE false END FROM DadoEmpresa d WHERE d.cnpj = :cnpj")
	boolean existeCnpj(@Param("cnpj") String cnpj);

	@Query("SELECT CASE WHEN COUNT(inscricaoEstadual) > 0 THEN true ELSE false END FROM DadoEmpresa d WHERE d.inscricaoEstadual = :inscricaoEstadual")
	boolean existeIe(@Param("inscricaoEstadual") String inscricaoEstadual);

	DadoEmpresa findByCnpj(String cnpj);

	Page<DadoEmpresa> findByCnpjContaining(String descricao, Pageable page);

	Page<DadoEmpresa> findByRazaoSocialIgnoreCaseContaining(String descricao, Pageable page);
}
