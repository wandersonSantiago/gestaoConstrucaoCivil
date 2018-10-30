package br.com.app.repository.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;

import br.com.app.dto.SaldoLancamentoDTO;
import br.com.app.entity.Empreendimento;
import br.com.app.entity.Lancamento;
import br.com.app.entity.QLancamento;
import br.com.app.entity.Usuario;
import br.com.app.enuns.TipoLancamentoEnum;
import br.com.app.pojo.SessionUsuario;
import br.com.app.repository.LancamentoRepository;
import br.com.app.repository.filter.LancamentoFilter;

@Service
public class LancamentoImpl{

	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	
	public Page<Lancamento> filters(LancamentoFilter filter) {
		List<BooleanExpression> geral = filtros(filter);
		if(geral.isEmpty()) {
			return lancamentoRepository.findAll(filter.getPage().getPageRequest());
		}		
		BooleanExpression addGeral = geral.get(0);
		for(BooleanExpression X : geral) {
			addGeral = addGeral.and(X);
		}		
		return lancamentoRepository.findAll(addGeral, filter.getPage().getPageRequest());
	}
	
	public List<BooleanExpression> filtros(LancamentoFilter filter){
		QLancamento qLancamento = QLancamento.lancamento;
		
		Usuario user = SessionUsuario.getInstance().getUsuario();
		
		List<BooleanExpression> geral = new ArrayList<>();
		
		BooleanExpression empreendimentoEquals = qLancamento.empreendimento.eq(user.getEmpreendimento());
		geral.add(empreendimentoEquals);
		
		if(filter.getDataCadastroDe() != null && filter.getDataCadastroAte() != null) {
			BooleanExpression dataCadastroEquals = qLancamento.dataCadastro.between(filter.getDataCadastroDe(), filter.getDataCadastroAte());
			geral.add(dataCadastroEquals);
		}
		if(filter.getDataPagamentoOuRecebimentoDe() != null && filter.getDataPagamentoOuRecebimentoAte() != null) {
			BooleanExpression dataPagamentoEquals = qLancamento.dataPagamentoOuRecebimento.between(filter.getDataPagamentoOuRecebimentoDe(), filter.getDataPagamentoOuRecebimentoAte());
			geral.add(dataPagamentoEquals);
		}
		if(filter.getDataVendimentoDe() != null && filter.getDataVendimentoAte() != null) {
			BooleanExpression dataVencimentoEquals = qLancamento.dataPagamentoOuRecebimento.between(filter.getDataVendimentoDe(), filter.getDataVendimentoAte());
			geral.add(dataVencimentoEquals);
		}
		if(filter.getValorDe()!= null && filter.getValorAte() != null) {
			BooleanExpression valorEquals = qLancamento.valor.between(filter.getValorDe(), filter.getValorAte());
			geral.add(valorEquals);
		}
		if(filter.getCategoria()!= null) {
			BooleanExpression categoriaEquals = qLancamento.categoria.eq(filter.getCategoria());
			geral.add(categoriaEquals);
		}
		if(filter.getDescricao()!= null) {
			BooleanExpression descricaoEquals = qLancamento.descricao.containsIgnoreCase(filter.getDescricao());
			geral.add(descricaoEquals);
		}
		if(filter.getTipo() != null) {
			BooleanExpression tipoEquals = qLancamento.tipo.eq(filter.getTipo());
			geral.add(tipoEquals);
		}
		
		return geral;
	}

	public SaldoLancamentoDTO estatistica(Date dataInicial, Date dataFinal, Empreendimento emp) {		
		
		return new SaldoLancamentoDTO(
				lancamentoRepository.somaSaldoDataInicioEDataFinal(TipoLancamentoEnum.CREDITO, emp.getId()), 
				lancamentoRepository.somaSaldoDataInicioEDataFinal(TipoLancamentoEnum.DEBITO, emp.getId()),
				lancamentoRepository.somaSaldoDataInicioEDataFinalPagoOuRecebido(TipoLancamentoEnum.CREDITO,dataInicial,dataFinal, emp.getId()),
				lancamentoRepository.somaSaldoDataInicioEDataFinalPagoOuRecebido(TipoLancamentoEnum.DEBITO,dataInicial,dataFinal, emp.getId()),
				lancamentoRepository.somaSaldoDataInicioEDataFinalNaoPagoOuNaoRecebido(TipoLancamentoEnum.CREDITO,dataInicial,dataFinal, emp.getId()),
				lancamentoRepository.somaSaldoDataInicioEDataFinalNaoPagoOuNaoRecebido(TipoLancamentoEnum.DEBITO,dataInicial,dataFinal, emp.getId()),
				lancamentoRepository.somaSaldoVencidaAPagar(emp.getId(), new Date(), TipoLancamentoEnum.DEBITO),
				lancamentoRepository.somaSaldoVencidaAReceber(emp.getId(), new Date(), TipoLancamentoEnum.CREDITO),
				dataInicial, dataFinal);
	}
	
	public Page<Lancamento> filterAndType(Date dataInicial, Date dataFinal, Empreendimento emp, String tipo, Pageable pageable){
		Page<Lancamento> page = null;
		if(tipo.equalsIgnoreCase("ENTRADA")) {
			page = lancamentoRepository.findByTipoAndDataVencimentoBetweenAndEmpreendimentoIdAndDataPagamentoOuRecebimentoIsNotNull(TipoLancamentoEnum.CREDITO,dataInicial,dataFinal, emp.getId(), pageable);
		}
		if(tipo.equalsIgnoreCase("SAIDA")) {
			page = lancamentoRepository.findByTipoAndDataVencimentoBetweenAndEmpreendimentoIdAndDataPagamentoOuRecebimentoIsNotNull(TipoLancamentoEnum.DEBITO,dataInicial,dataFinal, emp.getId(), pageable);
		}
		if(tipo.equalsIgnoreCase("ENTRADA_FUTURA")) {
			page = lancamentoRepository.findByTipoAndDataVencimentoBetweenAndEmpreendimentoIdAndDataPagamentoOuRecebimentoIsNull(TipoLancamentoEnum.CREDITO, dataInicial, dataFinal, emp.getId(), pageable);
		}
		if(tipo.equalsIgnoreCase("SAIDA_FUTURA")) {
			page = lancamentoRepository.findByTipoAndDataVencimentoBetweenAndEmpreendimentoIdAndDataPagamentoOuRecebimentoIsNull(TipoLancamentoEnum.DEBITO, dataInicial, dataFinal, emp.getId(), pageable);
		}
		if(tipo.equalsIgnoreCase("VENCIDAS_A_RECEBER")) {
			page = lancamentoRepository.findByTipoAndDataVencimentoBeforeAndEmpreendimentoIdAndDataPagamentoOuRecebimentoIsNull(TipoLancamentoEnum.CREDITO,new Date(), emp.getId(), pageable);
		}
		if(tipo.equalsIgnoreCase("VENCIDAS_A_PAGAR")) {
			page = lancamentoRepository.findByTipoAndDataVencimentoBeforeAndEmpreendimentoIdAndDataPagamentoOuRecebimentoIsNull(TipoLancamentoEnum.DEBITO,new Date(), emp.getId(), pageable);
		}
		return page;
	}

}
