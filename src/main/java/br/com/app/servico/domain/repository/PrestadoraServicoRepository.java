package br.com.app.servico.domain.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.servico.domain.model.PrestadoraServico;

public interface PrestadoraServicoRepository extends JpaRepository<PrestadoraServico,Long>{

	Page<PrestadoraServico> findByDadoEmpresaCnpjContaining(String descricao, Pageable page);

	Page<PrestadoraServico> findByDadoEmpresaRazaoSocialIgnoreCaseContaining(String descricao, Pageable page);

	 
	Boolean existsByDadoEmpresaCnpj(String cnpj);

	Optional<PrestadoraServico> findByDadoEmpresaCnpj(String cnpj);

}
