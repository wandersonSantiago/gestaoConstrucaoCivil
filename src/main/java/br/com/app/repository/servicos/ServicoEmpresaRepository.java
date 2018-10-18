package br.com.app.repository.servicos;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.app.entity.Estrutura;
import br.com.app.entity.servicos.ServicoEmpresa;

public interface ServicoEmpresaRepository extends JpaRepository<ServicoEmpresa,Long>{

	
	@Query("From ServicoEmpresa servico where servico.prestadoraServico.id = ?1 AND servico.dataEncerramentoServico != null")
	Collection<ServicoEmpresa> findByPrestadoraServico_id(Long id);

	//@Query("From ServicoEmpresa servico where servico.prestadoraServico.id = ?1 AND servico.dataFechamento != null")
	Collection<ServicoEmpresa> findByPrestadoraServico_idAndDataFechamentoNotNullAndDataPagamentoNull(Long id);
	
	Collection<ServicoEmpresa> findByEmpreendimentoId(Long id);

	Collection<ServicoEmpresa> findByEstrutura(Estrutura estrutura);

	Page<ServicoEmpresa> findByEstruturaDescricaoIgnoreCaseContaining(String descricao, Pageable page);

	Page<ServicoEmpresa> findById(String descricao, Pageable page);


}
