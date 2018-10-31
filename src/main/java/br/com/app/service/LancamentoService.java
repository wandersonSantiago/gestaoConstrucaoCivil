package br.com.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.dto.SaldoLancamentoDTO;
import br.com.app.entity.ConfiguracaoEmpreendimento;
import br.com.app.entity.Empreendimento;
import br.com.app.entity.Lancamento;
import br.com.app.entity.Usuario;
import br.com.app.pojo.MensagemException;
import br.com.app.pojo.SessionUsuario;
import br.com.app.repository.ConfiguracaoEmpreendimentoRepository;
import br.com.app.repository.LancamentoRepository;
import br.com.app.repository.filter.LancamentoFilter;
import br.com.app.repository.helper.LancamentoImpl;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	@Autowired
	private LancamentoImpl lancamentoImpl;
	@Autowired
	private ConfiguracaoEmpreendimentoRepository configuracaoRepository;

	@Transactional(readOnly = false)
	public  Lancamento insert(Lancamento lancamento) {
		Usuario user = SessionUsuario.getInstance().getUsuario();
		lancamento.setEmpreendimento(user.getEmpreendimento());
		lancamento.setUsuario(user);
		lancamento.setDataCadastro(new Date());
		lancamento.setValorTotal(lancamento.somaValorTotal());
		return lancamentoRepository.save(lancamento);
	}

	@Transactional(readOnly = false)
	public void update(Lancamento obj, Long id) {
		findById(id);
		obj.setValorTotal(obj.somaValorTotal());
		lancamentoRepository.save(obj);
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		lancamentoRepository.deleteById(id);
	}
	
	public Lancamento findById(Long id) {
		return lancamentoRepository.findById(id)
				.orElseThrow(() -> new MensagemException("Não encontrado!"));
	}

	public List<Lancamento> findAll() {
		return lancamentoRepository.findAll();
	}

	public Page<Lancamento> filters(LancamentoFilter filter) {
		Page<Lancamento> list = lancamentoImpl.filters(filter);		
		if(list == null || list.getNumberOfElements() < 1) {
			throw new MensagemException("Não foi encontrado nenhuma resultado!");
		}
		return list;
	}

	public SaldoLancamentoDTO estatistica() {
		Empreendimento emp = SessionUsuario.getInstance().getUsuario().getEmpreendimento();
		ConfiguracaoEmpreendimento config = configuracaoRepository.findByEmpreendimentoId(emp.getId());
		Date dataInicial = config.getDataBaseInicial();
	    Date dataFinal = config.getDataBaseFinal();
		return lancamentoImpl.estatistica(dataInicial, dataFinal, emp);	
	}

	public Page<Lancamento> filterAndType(String tipo, Pageable page) {
		Empreendimento emp = SessionUsuario.getInstance().getUsuario().getEmpreendimento();
		ConfiguracaoEmpreendimento config = configuracaoRepository.findByEmpreendimentoId(emp.getId());
		Date dataInicial = config.getDataBaseInicial();
	    Date dataFinal = config.getDataBaseFinal();
		return lancamentoImpl.filterAndType(dataInicial, dataFinal, emp, tipo, page);	
	}


	
}
