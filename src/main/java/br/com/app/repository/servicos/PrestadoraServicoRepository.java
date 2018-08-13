package br.com.app.repository.servicos;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.entity.servicos.PrestadoraServico;

public interface PrestadoraServicoRepository extends JpaRepository<PrestadoraServico,Long>{

	Page<PrestadoraServico> findByDadoEmpresaCnpjContaining(String descricao, Pageable page);

	Page<PrestadoraServico> findByDadoEmpresaRazaoSocialIgnoreCaseContaining(String descricao, Pageable page);

	Boolean existsByDadoEmpresaCnpj(String cnpj);

	Optional<PrestadoraServico> findByDadoEmpresaCnpj(String cnpj);

}
