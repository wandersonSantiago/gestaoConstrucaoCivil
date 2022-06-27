package br.com.app.financeiro.domain.service;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.commons.domain.model.Usuario;
import br.com.app.financeiro.domain.enuns.StatusCotacao;
import br.com.app.financeiro.domain.model.Cotacao;
import br.com.app.financeiro.domain.model.Lancamento;
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

		cotacaoRecuperada.getItens().forEach(item -> {
			cotacaoItemRepository.deleteById(item.getId());
		});

		cotacao.adicionarCotacaoNoItem();
		cotacaoRepository.save(cotacao);
	}

	public Cotacao buscaPorId(Long id) {
		return cotacaoRepository.findById(id).orElseThrow(() -> new NotFoundException("Cotação não encontrada"));
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
		return cotacaoRepository.findAll(filtrar(filter), page);
	}

	public Iterable<Cotacao> listFilter(CotacaoFilter filter) {
		return cotacaoRepository.findAll(filtrar(filter));
	}

	private Specification<Cotacao> filtrar(CotacaoFilter filter) {
		return (root, query, builder) -> {
			if (Lancamento.class.equals(query.getResultType())) {
			}

			Usuario user = SessionUsuario.getInstance().getUsuario();

			var predicates = new ArrayList<Predicate>();

			predicates.add(builder.equal(root.get("empreendimento"), user.getEmpreendimento()));

			if (filter.getDataCadastroDe() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("dataCadastro"), filter.getDataCadastroDe()));
			}

			if (filter.getDataCadastroAte() != null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("dataCadastro"), filter.getDataCadastroAte()));
			}

			if (filter.getDataFechamentoDe() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("dataFechamento"), filter.getDataFechamentoDe()));
			}

			if (filter.getDataFechamentoAte() != null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("dataFechamento"), filter.getDataFechamentoAte()));
			}

			if (filter.getDataLimiteDe() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("dataLimite"), filter.getDataLimiteDe()));
			}

			if (filter.getDataLimiteAte() != null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("dataLimite"), filter.getDataLimiteAte()));
			}

			if (filter.getDescricaoItem() != null) {
				predicates.add(builder.like(root.join("itens").get("descricao"),  "%" + filter.getDescricaoItem() + "%"));
			}

			if (filter.getStatus() != null) {
				predicates.add(builder.equal(root.get("statusCotacao"), filter.getStatus()));
			}

			if (filter.getTema() != null) {
				predicates.add(builder.like(root.get("tema"), "%" + filter.getTema() +  "%" ));
			}

			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
