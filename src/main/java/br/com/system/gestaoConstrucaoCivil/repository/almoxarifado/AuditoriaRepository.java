package br.com.system.gestaoConstrucaoCivil.repository.almoxarifado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Auditoria;
import br.com.system.gestaoConstrucaoCivil.enuns.TipoMovimentacaoEnum;

public interface AuditoriaRepository extends JpaRepository<Auditoria, Long>{

	Page<Auditoria> findByEmpreendimento_idAndTipoMovimentacao(Long idEmpreendimento,
			TipoMovimentacaoEnum tipoMovimentacao, Pageable page);


	

}
