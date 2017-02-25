package br.com.system.gestaoConstrucaoCivil.service.chamado;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.chamado.ChamadoTi;
import br.com.system.gestaoConstrucaoCivil.enuns.chamado.StatusChamado;
import br.com.system.gestaoConstrucaoCivil.pojo.ConverteData;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;
import br.com.system.gestaoConstrucaoCivil.repository.chamado.ChamadoTiRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ChamadoTiService {

	
	@Autowired
	private ChamadoTiRepository chamadoTiRepository;
	
	private Date dataAtual;
	@Transactional(readOnly = false)
	public void salvarEditar(ChamadoTi chamadoTi){
		dataAtual = new Date();
		chamadoTi.setEmpreendimento(SessionUsuario.getInstance().getUsuario().getEmpreendimento());
		chamadoTi.setUsuarioSolicitante(SessionUsuario.getInstance().getUsuario());
		chamadoTi.setStatus(StatusChamado.ABERTO);
		chamadoTi.setLido(false);
		chamadoTi.setSilenciar(false);
		chamadoTi.setDataAbertura(dataAtual);
		adicionarChamadoNasMensagens(chamadoTi);
		
		chamadoTiRepository.save(chamadoTi);
	}
	
	@Transactional(readOnly = false)
	public void servicos(ChamadoTi chamadoTi){
		chamadoTiRepository.save( chamadoTi);
	}
	
	@Transactional(readOnly = false)
	public void mensagens(ChamadoTi chamadoTi){
		adicionarChamadoNasMensagens(chamadoTi);	
		chamadoTiRepository.save(chamadoTi);
	}
	@Transactional(readOnly = false)
	public void atenderChamado(ChamadoTi chamadoTi){	
		chamadoTi.setStatus(StatusChamado.EM_ANDAMENTO);
		chamadoTi.setLido(true);
		chamadoTi.setUsuarioAtendente(SessionUsuario.getInstance().getUsuario());
		chamadoTiRepository.save(chamadoTi);
	}
	
	@Transactional(readOnly = false)
	public void fecharChamado(ChamadoTi chamadoTi){
		dataAtual = new Date();	
		chamadoTi.setStatus(StatusChamado.FECHADO);
		chamadoTi.setDataFechamento(dataAtual);	
		chamadoTiRepository.save(chamadoTi);
	}
	
	@Transactional(readOnly = false)
	public void silenciarChamadoTrue(ChamadoTi chamado){
		chamado.setSilenciar(true);
		chamadoTiRepository.save(chamado);
	}
	
	@Transactional(readOnly = false)
	public void silenciarChamadoFalse(ChamadoTi chamado){
		chamado.setSilenciar(false);
		chamadoTiRepository.save(chamado);
	}
	
	
	public Collection<ChamadoTi> listaChamadoTiUsuario(){
		return chamadoTiRepository.listaChamadoUsuario(SessionUsuario.getInstance().getUsuario(), SessionUsuario.getInstance().getUsuario().getEmpreendimento());
	}
	
	public Collection<ChamadoTi> listaSuporte(){
		return chamadoTiRepository.listaSuporte(SessionUsuario.getInstance().getUsuario().getEmpreendimento());
	}
	
	public ChamadoTi buscaPorId(Long id){
		return chamadoTiRepository.findOne(id);
	}
	
	public void adicionarChamadoNasMensagens(ChamadoTi chamadoTi){
		for(int i = 0; i < chamadoTi.getMensagens().size() ; i ++){
			chamadoTi.getMensagens().get(i).setChamado(chamadoTi);
			chamadoTi.getMensagens().get(i).setData(new Date());
			chamadoTi.getMensagens().get(i).setUsuario(SessionUsuario.getInstance().getUsuario());
		}
	}

	public Collection<ChamadoTi> relatorio(Date dataInicial, Date dataFinal) {
		
		return chamadoTiRepository.relatorio(new ConverteData(dataInicial).getString(),new ConverteData(dataFinal).getString());
	}
}
