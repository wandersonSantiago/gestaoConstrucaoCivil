package br.com.app.ticket.domain.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.app.commons.domain.model.Empreendimento;
import br.com.app.commons.domain.model.Usuario;
import br.com.app.ticket.domain.model.ChamadoTi;


public interface ChamadoTiRepository extends JpaRepository<ChamadoTi, Long> {

	@Query("FROM ChamadoTi chamado WHERE chamado.usuarioSolicitante = :usuario AND chamado.empreendimento = :empreendimento")
	Collection<ChamadoTi> listaChamadoUsuario(@Param(value = "usuario") Usuario usuario,
			@Param(value = "empreendimento") Empreendimento empreendimento);

	@Query("FROM ChamadoTi chamado WHERE chamado.empreendimento = :empreendimento AND chamado.status = 'EM_ANDAMENTO' OR chamado.status = 'ABERTO'")
	Collection<ChamadoTi> listaSuporte(@Param(value = "empreendimento") Empreendimento empreendimento);
	
	@Query("FROM ChamadoTi chamado WHERE CAST(CAST(chamado.dataAbertura as date) as string) >= :dataInicial AND CAST(CAST(chamado.dataAbertura as date) as string) <= :dataFinal")
	Collection<ChamadoTi> relatorio(@Param(value = "dataInicial") String dataInicial, @Param(value = "dataFinal") String dataFinal);

	@Query("FROM ChamadoTi chamado WHERE chamado.empreendimento.id = ?1 ")
	Page<ChamadoTi> buscarPoEmpreendimentoComPaginacao(Long id, Pageable page);
	
	@Query("FROM ChamadoTi chamado WHERE CAST(CAST(chamado.dataAbertura as date) as string) >= :dataInicial AND CAST(CAST(chamado.dataAbertura as date) as string) <= :dataFinal AND chamado.titulo = :titulo")
	Collection<ChamadoTi> relatorioPorDataETitulo(@Param(value = "dataInicial") String dataInicial, @Param(value = "dataFinal") String dataFinal, @Param(value = "titulo")String titulo);
	
	
	
}
