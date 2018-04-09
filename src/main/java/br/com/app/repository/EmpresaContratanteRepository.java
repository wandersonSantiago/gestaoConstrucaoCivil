package br.com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.app.entity.EmpresaContratante;

public interface EmpresaContratanteRepository extends JpaRepository<EmpresaContratante,Long> {

	@Query("SELECT empre FROM EmpresaContratante empre JOIN empre.dadoEmpresa dado WHERE "
			+ " dado.razaoSocial = ?1 OR dado.nomeFantasia = ?1 OR dado.cnpj = ?1 "
			+ " OR  dado.inscricaoEstadual = ?1 OR dado.ufIe = ?1 OR dado.telefone = ?1")
	List<EmpresaContratante> buscarPorDescricao(String descricao);
	
}
