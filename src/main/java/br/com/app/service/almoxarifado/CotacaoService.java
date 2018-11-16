package br.com.app.service.almoxarifado;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.dsl.BooleanExpression;

import br.com.app.entity.Usuario;
import br.com.app.entity.almoxarifado.Cotacao;
import br.com.app.entity.almoxarifado.QCotacao;
import br.com.app.enuns.StatusCotacao;
import br.com.app.exceptions.NotFoundException;
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


	public Page<Cotacao> pageFilter(CotacaoFilter filter, PageRequest page) {
		List<BooleanExpression> geral = filtros(filter);
		if(geral.isEmpty()) {
			return cotacaoRepository.findAll(page);
		}		
		BooleanExpression addGeral = geral.get(0);
		for(BooleanExpression X : geral) {
			addGeral = addGeral.and(X);
		}		
		return cotacaoRepository.findAll(addGeral, page);
	}
	
	public Iterable<Cotacao> listFilter(CotacaoFilter filter) {
		List<BooleanExpression> geral = filtros(filter);
		if(geral.isEmpty()) {
			return cotacaoRepository.findAll();
		}		
		BooleanExpression addGeral = geral.get(0);
		for(BooleanExpression X : geral) {
			addGeral = addGeral.and(X);
		}		
		return cotacaoRepository.findAll(addGeral);
	}
	
	public List<BooleanExpression> filtros(CotacaoFilter filter){
		QCotacao qCotacao = QCotacao.cotacao;
		
		Usuario user = SessionUsuario.getInstance().getUsuario();
		
		List<BooleanExpression> geral = new ArrayList<>();
		
		BooleanExpression empreendimentoEquals = qCotacao.empreendimento.eq(user.getEmpreendimento());
		geral.add(empreendimentoEquals);
		
		if(filter.getDataCadastroDe() != null && filter.getDataCadastroAte() != null) {
			BooleanExpression dataCriacaoEquals = qCotacao.dataCriacao.between(filter.getDataCadastroDe(), filter.getDataCadastroAte());
			geral.add(dataCriacaoEquals);
		}
		if(filter.getDataFechamentoDe() != null && filter.getDataFechamentoAte() != null) {
			BooleanExpression dataFechamentoEquals = qCotacao.dataFechamento.between(filter.getDataFechamentoDe(), filter.getDataFechamentoAte());
			geral.add(dataFechamentoEquals);
		}
		if(filter.getDataLimiteDe() != null && filter.getDataLimiteAte() != null) {
			BooleanExpression dataLimiteEquals = qCotacao.dataLimite.between(filter.getDataLimiteDe(), filter.getDataLimiteAte());
			geral.add(dataLimiteEquals);
		}
		if(filter.getDescricaoItem() != null) {		
				BooleanExpression cotacaoEquals = qCotacao.itens.any().descricao.containsIgnoreCase(filter.getDescricaoItem());
				geral.add(cotacaoEquals);	
		}
		if(filter.getStatus() != null) {
			BooleanExpression statusEquals = qCotacao.statusCotacao.eq(filter.getStatus());
			geral.add(statusEquals);
		}
		if(filter.getTema() != null) {
			BooleanExpression temaEquals = qCotacao.tema.containsIgnoreCase(filter.getTema());
			geral.add(temaEquals);
		}	
		
		return geral;
	}

}
