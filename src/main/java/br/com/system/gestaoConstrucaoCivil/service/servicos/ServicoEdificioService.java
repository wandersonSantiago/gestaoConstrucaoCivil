package br.com.system.gestaoConstrucaoCivil.service.servicos;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.servicos.ServicoEdificio;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;
import br.com.system.gestaoConstrucaoCivil.regras.servicos.ValidacaoServico;
import br.com.system.gestaoConstrucaoCivil.repository.servicos.ServicoEdificioRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ServicoEdificioService {

	@Autowired
	private ServicoEdificioRepository servicoEdificioRepository;

	@Autowired
	private ValidacaoServico<ServicoEdificio> validacao;
	
	public Page<ServicoEdificio> buscarTodos(PageRequest pages) {

		return servicoEdificioRepository.findAll(pages);
	}
	public ServicoEdificio buscarPorId(Long id) {
		
		return servicoEdificioRepository.findOne(id);
	}

	public Iterable<ServicoEdificio> lista() {
		return servicoEdificioRepository.findAll();
	}

	public Iterable<ServicoEdificio> buscarServicosPorApartamento(Integer torre, Integer andar, Integer apartamento) {
		
		 return servicoEdificioRepository.findByTorreAndAndarAndApartamento(torre, andar, apartamento);
	}
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(ServicoEdificio servico) {
		servico.setEmpreendimento(SessionUsuario.getInstance().getUsuario().getEmpreendimento());
		servico.setDataCadastro(new Date());
		servico.setValorPacoteServico(servico.getPacoteServico().getValor());
		validacao.verificarExistePacoteParaEmpresa(servico);
		servicoEdificioRepository.save(servico);
	}

	@Transactional(readOnly = false)
	public void salvarOuEditarVistoria(ServicoEdificio servico) {
		for(int i = 0; i < servico.getOcorrencias().size() ; i++){
			servico.getOcorrencias().get(i).setData(new Date());
			servico.getOcorrencias().get(i).setUsuario(SessionUsuario.getInstance().getUsuario());
			servico.getOcorrencias().get(i).setServicoEmpresa(servico);
		}
		if(servico.getPorcentagem() == 100){
			servico.setDataFechamento(new Date());
		}
		servicoEdificioRepository.save(servico);
		
	}
	public void salvarPagamento(ServicoEdificio servico) {
		servico.setDataPagamento(new Date());
		servicoEdificioRepository.save(servico);
		
	}
}