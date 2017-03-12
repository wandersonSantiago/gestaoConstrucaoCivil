package br.com.system.gestaoConstrucaoCivil.repository.servicos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.system.gestaoConstrucaoCivil.entity.servicos.ServicoEmpresa;

public interface ServicoEmpresaRepository extends JpaRepository<ServicoEmpresa,Long>{

	
	@Query("From ServicoEmpresa servico where servico.prestadoraServico.id = ?1 AND servico.dataFechamento != null")
	Iterable<ServicoEmpresa> findByPrestadoraServico_id(Long id);

}
