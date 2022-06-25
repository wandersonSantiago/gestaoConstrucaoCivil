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
import br.com.app.ticket.domain.model.ChamadoTi;
import br.com.app.ticket.domain.repository.ChamadoTiRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ChamadoTiService {

	@Autowired
	private ChamadoTiRepository chamadoTiRepository;

	private Date dataAtual;

	@Transactional(readOnly = false)
	public void salvarEditar(ChamadoTi chamadoTi) {
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
	public void servicos(ChamadoTi chamadoTi) {
		chamadoTiRepository.save(chamadoTi);
	}

	@Transactional(readOnly = false)
	public void mensagens(ChamadoTi chamadoTi) {
		adicionarChamadoNasMensagens(chamadoTi);
		chamadoTiRepository.save(chamadoTi);
	}

	@Transactional(readOnly = false)
	public void atenderChamado(ChamadoTi chamadoTi) {
		chamadoTi.setStatus(StatusChamado.EM_ANDAMENTO);
		chamadoTi.setLido(true);
		chamadoTi.setUsuarioAtendente(SessionUsuario.getInstance().getUsuario());
		chamadoTiRepository.save(chamadoTi);
	}

	@Transactional(readOnly = false)
	public void fecharChamado(ChamadoTi chamadoTi) {
		dataAtual = new Date();
		chamadoTi.setStatus(StatusChamado.FECHADO);
		chamadoTi.setDataFechamento(dataAtual);
		chamadoTiRepository.save(chamadoTi);
	}

	@Transactional(readOnly = false)
	public void silenciarChamadoTrue(ChamadoTi chamado) {
		chamado.setSilenciar(true);
		chamadoTiRepository.save(chamado);
	}

	@Transactional(readOnly = false)
	public void silenciarChamadoFalse(ChamadoTi chamado) {
		chamado.setSilenciar(false);
		chamadoTiRepository.save(chamado);
	}

	public Collection<ChamadoTi> listaChamadoTiUsuario() {
		return chamadoTiRepository.listaChamadoUsuario(SessionUsuario.getInstance().getUsuario(),
				SessionUsuario.getInstance().getUsuario().getEmpreendimento());
	}

	public Collection<ChamadoTi> listaSuporte() {
		return chamadoTiRepository.listaSuporte(SessionUsuario.getInstance().getUsuario().getEmpreendimento());
	}

	public Optional<ChamadoTi> buscaPorId(Long id) {

		return chamadoTiRepository.findById(id);
	}

	public void adicionarChamadoNasMensagens(ChamadoTi chamadoTi) {
		for (int i = 0; i < chamadoTi.getMensagens().size(); i++) {
			chamadoTi.getMensagens().get(i).setChamado(chamadoTi);
			chamadoTi.getMensagens().get(i).setData(new Date());
			chamadoTi.getMensagens().get(i).setUsuario(SessionUsuario.getInstance().getUsuario());
		}
	}

	public Collection<ChamadoTi> relatorio(Date dataInicial, Date dataFinal) {

		return chamadoTiRepository.relatorio(new ConverteData(dataInicial).getString(),
				new ConverteData(dataFinal).getString());
	}

	public Page<ChamadoTi> buscarPoEmpreendimentoComPaginacao(PageRequest pageRequest) {
		return chamadoTiRepository.buscarPoEmpreendimentoComPaginacao(
				SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId(), pageRequest);
	}

	public Collection<ChamadoTi> relatorioPorDataETitulo(Date dataInicial, Date dataFinal, String titulo) {
		return chamadoTiRepository.relatorioPorDataETitulo(new ConverteData(dataInicial).getString(),
				new ConverteData(dataFinal).getString(), titulo);
	}

}
