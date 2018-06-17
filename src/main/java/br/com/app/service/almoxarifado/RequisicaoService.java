package br.com.app.service.almoxarifado;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.almoxarifado.Requisicao;
import br.com.app.enuns.StatusRequisicao;
import br.com.app.pojo.SessionUsuario;
import br.com.app.repository.almoxarifado.RequisicaoRepository;
import br.com.app.util.gerador.codigo.GeraNumeroRequisicao;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class RequisicaoService {

	@Autowired
	private RequisicaoRepository requisicaoRepository;
	@Autowired
	private BaixaEstoqueService baixaEstoque;

	@Transactional(readOnly = false)
	public void aceitar(Integer  numeroRequisicao) {
		
		Requisicao requisicao = requisicaoRepository.findByNumeroRequisicao(numeroRequisicao);
		if (requisicao.getStatusRequisicao() == StatusRequisicao.PENDENTE) {
			baixaEstoque.baixar(requisicao);
			requisicao.setStatusRequisicao(StatusRequisicao.EFETUADO);
			requisicao.setDataSaida(new Date());
			requisicaoRepository.save(requisicao);
		}

	}

	@Transactional(readOnly = false)
	public void rejeitar(Integer numeroRequisicao) {
		Requisicao requisicao = requisicaoRepository.findByNumeroRequisicao(numeroRequisicao);
		requisicao.setStatusRequisicao(StatusRequisicao.RECUSADO);
		requisicaoRepository.save(requisicao);
	}

	@Transactional(readOnly = false)
	public void insert(Requisicao requisicao) {

		requisicao.setStatusRequisicao(StatusRequisicao.PENDENTE);
		GeraNumeroRequisicao gerarNumero = new GeraNumeroRequisicao();
		requisicao.setNumeroRequisicao(gerarNumero.gerarNumeroRequisicao());
		requisicao.setDataCriacao(new Date());
		
		
		requisicaoRepository.save(requisicao);

	}

	public Page<Requisicao> buscarTodosComPaginacao(PageRequest pageRequest) {
		return requisicaoRepository.buscarTodasRequisicoesComPaginacao(
				SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId(), pageRequest);
	}

	public Collection<Requisicao> buscarTodos() {

		return requisicaoRepository
				.findByEmpreendimento(SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId());

	}
	public Requisicao findByNumeroRequicao(Integer numeroRequisicao) {
		return requisicaoRepository.findByNumeroRequisicao(numeroRequisicao);
	}
	public Optional<Requisicao> buscarPorId(Long id) {
		return requisicaoRepository.findById(id);
	}

}
