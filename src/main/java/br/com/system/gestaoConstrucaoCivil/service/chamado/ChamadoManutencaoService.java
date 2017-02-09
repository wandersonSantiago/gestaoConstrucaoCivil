package br.com.system.gestaoConstrucaoCivil.service.chamado;


import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.chamado.ChamadoManutencao;
import br.com.system.gestaoConstrucaoCivil.enuns.chamado.StatusChamado;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;
import br.com.system.gestaoConstrucaoCivil.repository.chamado.ChamadoManutencaoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ChamadoManutencaoService {

	
	@Autowired
	private ChamadoManutencaoRepository chamadoManutencaoRepository;
	
		
	@Transactional(readOnly = false)
	public void salvarEditar(ChamadoManutencao chamadoManutencao){
		Date dataAtual = new Date();
		chamadoManutencao.setEmpreendimento(SessionUsuario.getInstance().getUsuario().getEmpreendimento());
		chamadoManutencao.setUsuarioSolicitante(SessionUsuario.getInstance().getUsuario());
		chamadoManutencao.setStatus(StatusChamado.ABERTO);
		chamadoManutencao.setLido(false);
		chamadoManutencao.setSilenciar(false);
		chamadoManutencao.setDataAbertura(dataAtual);
		adicionarChamadoNasMensagens(chamadoManutencao);
	
		chamadoManutencaoRepository.save(chamadoManutencao);
	}
	
	@Transactional(readOnly = false)
	public void servicos(ChamadoManutencao chamadoManutencao){
		chamadoManutencaoRepository.save(chamadoManutencao);
	}
	
	@Transactional(readOnly = false)
	public void mensagens(ChamadoManutencao chamadoManutencao){
		adicionarChamadoNasMensagens(chamadoManutencao);	
		chamadoManutencaoRepository.save(chamadoManutencao);
	}
	@Transactional(readOnly = false)
	public void atenderChamado(ChamadoManutencao chamadoManutencao){
		chamadoManutencao.setStatus(StatusChamado.EM_ANDAMENTO);
		chamadoManutencao.setLido(true);
		chamadoManutencao.setUsuarioAtendente(SessionUsuario.getInstance().getUsuario());
		chamadoManutencaoRepository.save(chamadoManutencao);
	}
	
	@Transactional(readOnly = false)
	public void fecharChamado(ChamadoManutencao chamadoManutencao){
		Date dataAtual = new Date();
		chamadoManutencao.setStatus(StatusChamado.FECHADO);
		chamadoManutencao.setDataFechamento(dataAtual);	
		chamadoManutencaoRepository.save(chamadoManutencao);
	}
	
	public Collection<ChamadoManutencao> listaChamadoManutencaoUsuario(){
		return chamadoManutencaoRepository.listaChamadoUsuario(SessionUsuario.getInstance().getUsuario(), SessionUsuario.getInstance().getUsuario().getEmpreendimento());
	}
	
	public Collection<ChamadoManutencao> listaSuporte(){
		return chamadoManutencaoRepository.listaSuporte(SessionUsuario.getInstance().getUsuario().getEmpreendimento());
	}
	
	public ChamadoManutencao buscaPorId(Long id){
		return chamadoManutencaoRepository.findOne(id);
	}
	
	@Transactional(readOnly = false)
	public void silenciarChamadoTrue(ChamadoManutencao chamado){
		chamado.setSilenciar(true);
		chamadoManutencaoRepository.save(chamado);
	}
	
	@Transactional(readOnly = false)
	public void silenciarChamadoFalse(ChamadoManutencao chamado){
		chamado.setSilenciar(false);
		chamadoManutencaoRepository.save(chamado);
	}
	
	public ChamadoManutencao adicionarChamadoNasMensagens(ChamadoManutencao chamadoManutencao){
		for(int i = 0 ; i < chamadoManutencao.getMensagens().size() ; i ++ ){
			chamadoManutencao.getMensagens().get(i).setChamado(chamadoManutencao);
			chamadoManutencao.getMensagens().get(i).setData(new Date());
			chamadoManutencao.getMensagens().get(i).setUsuario(SessionUsuario.getInstance().getUsuario());
		}
		return chamadoManutencao;
	}
	
	public Page<ChamadoManutencao> relatorio(PageRequest page) {
		return chamadoManutencaoRepository.findAll(page);
	}

}
