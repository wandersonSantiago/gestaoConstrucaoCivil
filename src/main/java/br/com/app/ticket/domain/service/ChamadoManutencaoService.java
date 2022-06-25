package br.com.app.ticket.domain.service;


import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.infraestructure.security.SessionUsuario;
import br.com.app.infraestructure.util.ConverteData;
import br.com.app.ticket.domain.enuns.StatusChamado;
import br.com.app.ticket.domain.model.ChamadoManutencao;
import br.com.app.ticket.domain.repository.ChamadoManutencaoRepository;

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
	
	public Optional<ChamadoManutencao> buscaPorId(Long id){
		
		return chamadoManutencaoRepository.findById(id);
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
	
	public Collection<ChamadoManutencao> relatorio(Date dataInicial,Date dataFinal) {		
		return chamadoManutencaoRepository.relatorio(new ConverteData(dataInicial).getString(),new ConverteData(dataFinal).getString());
	}

	public Page<ChamadoManutencao> buscarPoEmpreendimentoComPaginacao(PageRequest pageRequest) {
		return chamadoManutencaoRepository.findByEmpreendimento_id(SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId() , pageRequest);
	}

	public Collection<ChamadoManutencao> relatorioPorDataETitulo(Date dataInicial, Date dataFinal, String titulo) {
		return chamadoManutencaoRepository.relatorioPorDataETitulo(new ConverteData(dataInicial).getString(),new ConverteData(dataFinal).getString(), titulo);
	}

}
