package br.com.app.service.almoxarifado;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.almoxarifado.Cotacao;
import br.com.app.enuns.StatusCotacao;
import br.com.app.pojo.SessionUsuario;
import br.com.app.repository.almoxarifado.CotacaoRepository;

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

	public Collection<Cotacao> buscarTodos() {
		return cotacaoRepository.findAll();
	}

	public Optional<Cotacao> buscaPorId(Long id) {
		return cotacaoRepository.findById(id);
	}

	@Transactional(readOnly = false)
	public void fecharCotacao(Long idCotacao) {
		Cotacao cotacao = cotacaoRepository.findById(idCotacao).get();
		cotacao.setStatusCotacao(StatusCotacao.FECHADO);
		cotacao.setDataFechamento(new Date());
		verificarItens.verificarGanhadores(cotacao);
		cotacaoRepository.save(cotacao);
	}

}
