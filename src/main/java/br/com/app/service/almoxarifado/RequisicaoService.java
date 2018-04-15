package br.com.app.service.almoxarifado;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.almoxarifado.IRequisicao;
import br.com.app.entity.almoxarifado.Requisicao;
import br.com.app.enuns.StatusRequisicao;
import br.com.app.pojo.SessionUsuario;
import br.com.app.repository.almoxarifado.RequisicaoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class RequisicaoService {

	@Autowired
	private RequisicaoRepository requisicaoRepository;
	@Autowired
	private BaixaEstoqueService baixaEstoque;
	
	
	@Transactional(readOnly = false)
	public void aceitar(IRequisicao requisicao)
	{
		if(requisicao.getInformacaoRequisicao().getStatusRequisicao() == StatusRequisicao.PENDENTE)
		{
		   baixaEstoque.baixar(requisicao);	
		   requisicao.getInformacaoRequisicao().statusAceito();
		   requisicaoRepository.save(requisicao.getInformacaoRequisicao());
		}
		
	}
	@Transactional(readOnly = false)
	public void rejeitar(IRequisicao requisicao)
	{
		requisicao.getInformacaoRequisicao().statusRejeitado();
		requisicaoRepository.save(requisicao.getInformacaoRequisicao());
	}
	 
	 
	@Transactional(readOnly = false)
	public void salvarOuEditar(Requisicao requisicao) {
		
		requisicao.novaRequisicao();
		requisicaoRepository.save(requisicao);

	}

	public Collection<Requisicao> buscarTodos() {
/*
		return requisicaoRepository
				.buscarTodasRequisicoes(SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId());*/
		return null;
	}

	public Optional<Requisicao> buscarPorId(Long id) {
		return requisicaoRepository.findById(id);
	}

	/*@Transactional(readOnly = false)
	public void aceitar(Integer numeroRequisicao) {
		requisicaoService.aceitar(requisicaoRepository.buscarPorNumeroRequisicao(numeroRequisicao));
	}

	@Transactional(readOnly = false)
	public void rejeitar(Integer numeroRequisicao) {
		requisicaoService.rejeitar(requisicaoRepository.buscarPorNumeroRequisicao(numeroRequisicao));
	}

	public Page<Requisicao> buscarTodosComPaginacao(PageRequest pageRequest) {
		return requisicaoRepository.buscarTodasRequisicoesComPaginacao(
				SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId(), pageRequest);
	}*/
}
