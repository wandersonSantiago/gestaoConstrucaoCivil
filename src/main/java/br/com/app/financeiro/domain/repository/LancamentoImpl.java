package br.com.app.financeiro.domain.repository;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.app.commons.domain.model.Empreendimento;
import br.com.app.commons.domain.model.Usuario;
import br.com.app.financeiro.api.v1.dto.SaldoLancamentoDTO;
import br.com.app.financeiro.domain.enuns.TipoLancamentoEnum;
import br.com.app.financeiro.domain.model.Lancamento;
import br.com.app.infraestructure.security.SessionUsuario;

@Service
public class LancamentoImpl {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	public Page<Lancamento> filters(LancamentoFilter filter) {

		return lancamentoRepository.findAll(filtrar(filter), filter.getPage().getPageRequest());
	}

	private Specification<Lancamento> filtrar(LancamentoFilter filter) {
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

			if (filter.getDataPagamentoOuRecebimentoDe() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("dataPagamentoOuRecebimento"),
						filter.getDataPagamentoOuRecebimentoDe()));
			}

			if (filter.getDataPagamentoOuRecebimentoAte() != null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("dataPagamentoOuRecebimento"),
						filter.getDataPagamentoOuRecebimentoAte()));
			}

			if (filter.getDataVendimentoDe() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("dataVencimento"), filter.getDataVendimentoDe()));
			}

			if (filter.getDataVendimentoAte() != null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("dataVencimento"), filter.getDataVendimentoAte()));
			}

			if (filter.getValorDe() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("valor"), filter.getValorDe()));
			}

			if (filter.getValorAte() != null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("valor"), filter.getValorAte()));
			}

			if (filter.getDescricao() != null) {
				predicates.add(builder.like(root.get("descricao"), "%" + filter.getDescricao().toUpperCase() + "%"));
			}

			if (filter.getCategoria() != null) {
				predicates.add(builder.equal(root.get("categoria"), filter.getCategoria()));
			}
			
			if (filter.getTipo() != null) {
				predicates.add(builder.equal(root.get("tipo"), filter.getTipo()));
			}

			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

	public SaldoLancamentoDTO estatistica(Date dataInicial, Date dataFinal, Empreendimento emp) {

		return new SaldoLancamentoDTO(
				lancamentoRepository.somaSaldoDataInicioEDataFinal(TipoLancamentoEnum.CREDITO, emp.getId()),
				lancamentoRepository.somaSaldoDataInicioEDataFinal(TipoLancamentoEnum.DEBITO, emp.getId()),
				lancamentoRepository.somaSaldoDataInicioEDataFinalPagoOuRecebido(TipoLancamentoEnum.CREDITO,
						dataInicial, dataFinal, emp.getId()),
				lancamentoRepository.somaSaldoDataInicioEDataFinalPagoOuRecebido(TipoLancamentoEnum.DEBITO, dataInicial,
						dataFinal, emp.getId()),
				lancamentoRepository.somaSaldoDataInicioEDataFinalNaoPagoOuNaoRecebido(TipoLancamentoEnum.CREDITO,
						dataInicial, dataFinal, emp.getId()),
				lancamentoRepository.somaSaldoDataInicioEDataFinalNaoPagoOuNaoRecebido(TipoLancamentoEnum.DEBITO,
						dataInicial, dataFinal, emp.getId()),
				lancamentoRepository.somaSaldoVencidaAPagar(emp.getId(), new Date(), TipoLancamentoEnum.DEBITO),
				lancamentoRepository.somaSaldoVencidaAReceber(emp.getId(), new Date(), TipoLancamentoEnum.CREDITO),
				dataInicial, dataFinal);
	}

	public Page<Lancamento> filterAndType(Date dataInicial, Date dataFinal, Empreendimento emp, String tipo,
			Pageable pageable) {
		Page<Lancamento> page = null;
		if (tipo.equalsIgnoreCase("ENTRADA")) {
			page = lancamentoRepository
					.findByTipoAndDataVencimentoBetweenAndEmpreendimentoIdAndDataPagamentoOuRecebimentoIsNotNull(
							TipoLancamentoEnum.CREDITO, dataInicial, dataFinal, emp.getId(), pageable);
		}
		if (tipo.equalsIgnoreCase("SAIDA")) {
			page = lancamentoRepository
					.findByTipoAndDataVencimentoBetweenAndEmpreendimentoIdAndDataPagamentoOuRecebimentoIsNotNull(
							TipoLancamentoEnum.DEBITO, dataInicial, dataFinal, emp.getId(), pageable);
		}
		if (tipo.equalsIgnoreCase("ENTRADA_FUTURA")) {
			page = lancamentoRepository
					.findByTipoAndDataVencimentoBetweenAndEmpreendimentoIdAndDataPagamentoOuRecebimentoIsNull(
							TipoLancamentoEnum.CREDITO, dataInicial, dataFinal, emp.getId(), pageable);
		}
		if (tipo.equalsIgnoreCase("SAIDA_FUTURA")) {
			page = lancamentoRepository
					.findByTipoAndDataVencimentoBetweenAndEmpreendimentoIdAndDataPagamentoOuRecebimentoIsNull(
							TipoLancamentoEnum.DEBITO, dataInicial, dataFinal, emp.getId(), pageable);
		}
		if (tipo.equalsIgnoreCase("VENCIDAS_A_RECEBER")) {
			page = lancamentoRepository
					.findByTipoAndDataVencimentoBeforeAndEmpreendimentoIdAndDataPagamentoOuRecebimentoIsNull(
							TipoLancamentoEnum.CREDITO, new Date(), emp.getId(), pageable);
		}
		if (tipo.equalsIgnoreCase("VENCIDAS_A_PAGAR")) {
			page = lancamentoRepository
					.findByTipoAndDataVencimentoBeforeAndEmpreendimentoIdAndDataPagamentoOuRecebimentoIsNull(
							TipoLancamentoEnum.DEBITO, new Date(), emp.getId(), pageable);
		}
		return page;
	}

}
