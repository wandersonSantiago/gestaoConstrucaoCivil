package br.com.app.financeiro.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.financeiro.domain.enuns.CotacaoEmpresaItemStatus;
import br.com.app.financeiro.domain.model.CotacaoEmpresa;
import br.com.app.financeiro.domain.model.CotacaoEmpresaItem;
import br.com.app.financeiro.domain.repository.CotacaoEmpresaRepository;
import br.com.app.infraestructure.exception.QuebraDeRegraException;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class CotacaoEmpresaService {

	@Autowired
	private CotacaoEmpresaRepository cotacaoEmpresaRepository;

	@Transactional(readOnly = false)
	public void salvarOuEditar(CotacaoEmpresa obj) {
		if(existsByFornecedorIdAndCotacaoId(obj.getFornecedor().getId(), obj.getCotacao().getId())) {
			throw new  QuebraDeRegraException("Não foi possivel realizar este lançamento, Ja existe uma cotação para esta empresa!");
		}
		
		for (CotacaoEmpresaItem item : obj.getItens()) {
			item.setId(null);
			item.setStatus(CotacaoEmpresaItemStatus.PENDENTE);
			item.setCotacaoEmpresa(obj);
		}
		cotacaoEmpresaRepository.save(obj);
	}
	
	@Transactional(readOnly = false)
	public void update(CotacaoEmpresa obj) {
		for (CotacaoEmpresaItem item : obj.getItens()) {
			item.setStatus(CotacaoEmpresaItemStatus.PENDENTE);
			item.setCotacaoEmpresa(obj);
		}
		cotacaoEmpresaRepository.save(obj);
	}

	public List<CotacaoEmpresa> buscarTodos() {

		return cotacaoEmpresaRepository.findAll();
	}

	public boolean existsByFornecedorIdAndCotacaoId(Long fornecedorId, Long cotacaoId) {
		return cotacaoEmpresaRepository.existsByFornecedorIdAndCotacaoId(fornecedorId,cotacaoId );
	}
	
	public CotacaoEmpresa buscarPorId(Long id, boolean somenteItensGanho) {
		
		CotacaoEmpresa cotacao = cotacaoEmpresaRepository.findById(id).get();
		
		if(somenteItensGanho) {
			cotacao.removerItensPerdedores();
		}
		return cotacao;
	}
	
	public CotacaoEmpresa buscarPorId(Long id) {		
		return cotacaoEmpresaRepository.findById(id).get();
	}

	public List<CotacaoEmpresa> ganhadores(Long idCotacao) {
		List<CotacaoEmpresa> cotacoes = cotacaoEmpresaRepository.buscarGanhadores(idCotacao);
		for (CotacaoEmpresa item : cotacoes) {
			
				item.removerItensPerdedores();
		}
		return cotacoes;
	}

	public List<CotacaoEmpresa> concorrentes(Long idCotacao) {
		return cotacaoEmpresaRepository.buscarPorCotacao(idCotacao);
	}

}
