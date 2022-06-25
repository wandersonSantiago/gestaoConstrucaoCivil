package br.com.app.financeiro.domain.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.financeiro.domain.enuns.StatusCotacao;
import br.com.app.financeiro.domain.model.Cotacao;
import br.com.app.financeiro.domain.repository.CotacaoFilter;
import br.com.app.financeiro.domain.repository.CotacaoItemRepository;
import br.com.app.financeiro.domain.repository.CotacaoRepository;
import br.com.app.infraestructure.exception.NotFoundException;
import br.com.app.infraestructure.security.SessionUsuario;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class CotacaoService {

	@Autowired
	private CotacaoRepository cotacaoRepository;
	@Autowired
	private CotacaoItemRepository cotacaoItemRepository;
	@Autowired
	private VerificaItensGanhadores verificarItens;

	@Transactional(readOnly = false)
	public void save(Cotacao cotacao) {

		cotacao.setDataCriacao(new Date());
		cotacao.setStatusCotacao(StatusCotacao.ABERTO);
		cotacao.setEmpreendimento(SessionUsuario.getInstance().getUsuario().getEmpreendimento());
		cotacao.adicionarCotacaoNoItem();

		cotacaoRepository.save(cotacao);
	}
	
	@Transactional(readOnly = false)
	public void update(Long id, Cotacao cotacao) {
		Cotacao cotacaoRecuperada = buscaPorId(id);		
		
		cotacaoRecuperada.getItens().forEach(item ->{
			cotacaoItemRepository.deleteById(item.getId());
		});
		
		cotacao.adicionarCotacaoNoItem();
		cotacaoRepository.save(cotacao);
	}

	public Cotacao buscaPorId(Long id) {
		return cotacaoRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Cotação não encontrada"));
	}

	@Transactional(readOnly = false)
	public void fecharCotacao(Long idCotacao) {
		Cotacao cotacao = cotacaoRepository.findById(idCotacao)
				.orElseThrow(() -> new NotFoundException("Cotação não encontrada"));
		cotacao.setStatusCotacao(StatusCotacao.FECHADO);
		cotacao.setDataFechamento(new Date());
		verificarItens.verificarGanhadores(cotacao);
		cotacaoRepository.save(cotacao);
	}

	public Page<Cotacao> pageFilter(CotacaoFilter filter, PageRequest page) {
//		List<BooleanExpression> geral = filtros(filter);
//		if (geral.isEmpty()) {
//			return cotacaoRepository.findAll(page);
//		}
//		BooleanExpression addGeral = geral.get(0);
//		for (BooleanExpression X : geral) {
//			addGeral = addGeral.and(X);
//		}
//		return cotacaoRepository.findAll(addGeral, page);
		return cotacaoRepository.findAll(page);
	}

	public Iterable<Cotacao> listFilter(CotacaoFilter filter) {
//		List<BooleanExpression> geral = filtros(filter);
//		if (geral.isEmpty()) {
//			return cotacaoRepository.findAll();
//		}
//		BooleanExpression addGeral = geral.get(0);
//		for (BooleanExpression X : geral) {
//			addGeral = addGeral.and(X);
//		}
//		return cotacaoRepository.findAll(addGeral);
		return cotacaoRepository.findAll();
	}

	//TODO: alterar para specification
//	public List<BooleanExpression> filtros(CotacaoFilter filter) {
//		QCotacao qCotacao = QCotacao.cotacao;
//
//		Usuario user = SessionUsuario.getInstance().getUsuario();
//
//		List<BooleanExpression> geral = new ArrayList<>();
//
//		BooleanExpression empreendimentoEquals = qCotacao.empreendimento.eq(user.getEmpreendimento());
//		geral.add(empreendimentoEquals);
//
//		if (filter.getDataCadastroDe() != null && filter.getDataCadastroAte() != null) {
//			BooleanExpression dataCriacaoEquals = qCotacao.dataCriacao.between(filter.getDataCadastroDe(),
//					filter.getDataCadastroAte());
//			geral.add(dataCriacaoEquals);
//		}
//		if (filter.getDataFechamentoDe() != null && filter.getDataFechamentoAte() != null) {
//			BooleanExpression dataFechamentoEquals = qCotacao.dataFechamento.between(filter.getDataFechamentoDe(),
//					filter.getDataFechamentoAte());
//			geral.add(dataFechamentoEquals);
//		}
//		if (filter.getDataLimiteDe() != null && filter.getDataLimiteAte() != null) {
//			BooleanExpression dataLimiteEquals = qCotacao.dataLimite.between(filter.getDataLimiteDe(),
//					filter.getDataLimiteAte());
//			geral.add(dataLimiteEquals);
//		}
//		if (filter.getDescricaoItem() != null) {
//			BooleanExpression cotacaoEquals = qCotacao.itens.any().descricao
//					.containsIgnoreCase(filter.getDescricaoItem());
//			geral.add(cotacaoEquals);
//		}
//		if (filter.getStatus() != null) {
//			BooleanExpression statusEquals = qCotacao.statusCotacao.eq(filter.getStatus());
//			geral.add(statusEquals);
//		}
//		if (filter.getTema() != null) {
//			BooleanExpression temaEquals = qCotacao.tema.containsIgnoreCase(filter.getTema());
//			geral.add(temaEquals);
//		}
//
//		return geral;
//	}

}
