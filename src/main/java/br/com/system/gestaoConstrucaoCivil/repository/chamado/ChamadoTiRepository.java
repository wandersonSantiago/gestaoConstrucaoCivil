package br.com.system.gestaoConstrucaoCivil.repository.chamado;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.Usuario;
import br.com.system.gestaoConstrucaoCivil.entity.chamado.ChamadoTi;

public interface ChamadoTiRepository extends JpaRepository<ChamadoTi, Long>{

	
	@Query("FROM ChamadoTi chamado WHERE chamado.usuarioSolicitante = :usuario AND chamado.empreendimento = :empreendimento")
	Collection<ChamadoTi> listaChamadoUsuario(@Param(value = "usuario") Usuario usuario, @Param(value = "empreendimento") Empreendimento empreendimento);
	
	@Query("FROM ChamadoTi chamado WHERE chamado.empreendimento = :empreendimento AND chamado.status = 'EM_ANDAMENTO' OR chamado.status = 'ABERTO'")
	Collection<ChamadoTi> listaSuporte(@Param(value="empreendimento") Empreendimento empreendimento);
}
