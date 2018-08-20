package br.com.app.service.almoxarifado;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.almoxarifado.Cotacao;
import br.com.app.entity.almoxarifado.QCotacao;
import br.com.app.enuns.StatusCotacao;
import br.com.app.exceptions.NotFoundException;
import br.com.app.pojo.MensagemException;
import br.com.app.pojo.SessionUsuario;
import br.com.app.repository.almoxarifado.CotacaoRepository;
import br.com.app.repository.filter.CotacaoFilter;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class CotacaoService {

	@Autowired
	private CotacaoRepository cotacaoRepository;
	@Autowired
	private VerificaItensGanhadores verificarItens;

	@Transactional(readOnly = false)
	public void salvaAltera(Cotacao cotacao) {

		cotacao.setDataCriacao(new Date());
		cotacao.setStatusCotacao(StatusCotacao.ABERTO);
		cotacao.setEmpreendimento(SessionUsuario.getInstance().getUsuario().getEmpreendimento());
		cotacao.adicionarCotacaoNoItem();

		cotacaoRepository.save(cotacao);
	}

	public Optional<Cotacao> buscaPorId(Long id) {
		return cotacaoRepository.findById(id);
	}

	@Transactional(readOnly = false)
	public void fecharCotacao(Long idCotacao) {
		Cotacao cotacao = cotacaoRepository.findById(idCotacao).orElseThrow(
				() -> new NotFoundException("Cotação não encontrada"));
		cotacao.setStatusCotacao(StatusCotacao.FECHADO);
		cotacao.setDataFechamento(new Date());
		verificarItens.verificarGanhadores(cotacao);
		cotacaoRepository.save(cotacao);
	}

	public Page<Cotacao> findAll(Pageable page) {
		return cotacaoRepository.findAll(page);
	}

	public Page<Cotacao> findByTemaIgnoreCase(String descricao, PageRequest page) {
		
		Page<Cotacao> list = cotacaoRepository.findByTemaIgnoreCaseContains(descricao, page);
		if(list == null || list.getNumberOfElements() < 1) {
			throw new MensagemException("Não foi encontrado nenhuma resultado para a busca  " + descricao);
		}
		return list;
	}

	public Page<Cotacao> filter(CotacaoFilter filter, PageRequest of) {
		QCotacao qCotacao = QCotacao.cotacao;
		
		if(filter.getDataFechamentoDe() != null && filter.getDataFechamentoAte() != null) {
			qCotacao.dataFechamento.between(filter.getDataFechamentoDe(), filter.getDataFechamentoAte());
		}
		
		return null;
	}

}
