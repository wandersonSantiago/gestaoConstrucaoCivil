package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Auditoria;
import br.com.system.gestaoConstrucaoCivil.enuns.TipoMovimentacaoEnum;
import br.com.system.gestaoConstrucaoCivil.pojo.MensagemException;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.AuditoriaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class AuditoriaService {

	

	@Autowired
	private AuditoriaRepository auditoriaRepository;
	
	
	public Page<Auditoria> findByEmpreendimento_idAndTipoMovimentacao(TipoMovimentacaoEnum tipoMovimentacao, PageRequest page){
		Long idEmpreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();
		Page<Auditoria> lista =  auditoriaRepository.findByEmpreendimento_idAndTipoMovimentacao(idEmpreendimento ,tipoMovimentacao, page );
		if(lista.getSize() < 1){
			throw new MensagemException("Não existem lançamentos" );
			
		}
		return lista;
	}
	
	public List<Auditoria> findAll(){
		return auditoriaRepository.findAll();
	}
}
