package br.com.app.service.servicos;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.servicos.PacoteServico;
import br.com.app.entity.servicos.ServicoEmpresa;
import br.com.app.pojo.MensagemException;
import br.com.app.pojo.SessionUsuario;
import br.com.app.repository.servicos.ServicoEmpresaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ServicoEmpresaService {

	@Autowired
	private ServicoEmpresaRepository servicoRepository;
	@Autowired
	private ValidacaoServico validacao;
	 

	@Transactional(readOnly = false)
	public void insert(ServicoEmpresa servico) {
		servico.setEmpreendimento(SessionUsuario.getInstance().getUsuario().getEmpreendimento());
		servico.setDataCadastro(new Date());
		servico.setValorPacoteServico(servico.getPacoteServico().getValor());
		validacao.verificarExistePacoteParaEmpresa(servico);
		servicoRepository.save(servico);
	}
	
	@Transactional(readOnly = false)
	public void update(ServicoEmpresa servico) {
		
		ServicoEmpresa servicoRecuperado = servicoRepository.getOne(servico.getId());
		
		if(!servico.getEstrutura().equals(servicoRecuperado.getEstrutura()) || !servico.getPacoteServico().equals(servicoRecuperado.getPacoteServico())) {
			validacao.verificarExistePacoteParaEmpresa(servico);
		}		
		servicoRepository.save(servico);
	}


	public Collection<ServicoEmpresa> lista() {
		return servicoRepository.findAll();
	}

	public Collection<ServicoEmpresa> buscarServicosDaPrestadora(Long id) {
		return servicoRepository.findByPrestadoraServico_id(id);
	}

	public ServicoEmpresa findById(Long id) {
		return servicoRepository.findById(id).get();
	}

	@Transactional(readOnly = false)
	public void salvarPagamento(ServicoEmpresa servico) {
		servico.setDataFechamento(new Date());
		servico.setValorTotalPago(
				servico.getValorAdicional() + servico.getValorPacoteServico() - servico.getValorDesconto());
		servicoRepository.save(servico);
	}

	public Collection<ServicoEmpresa> buscarServicosPagamentoLiberadoDaPrestadora(Long id) {
		return servicoRepository.findByPrestadoraServico_idAndDataFechamentoNotNullAndDataPagamentoNull(id);
	}

	@Transactional(readOnly = false)
	public void efetuarPagamento(Long id) {
		List<ServicoEmpresa> servicos = (List<ServicoEmpresa>) servicoRepository
				.findByPrestadoraServico_idAndDataFechamentoNotNullAndDataPagamentoNull(id);
		for (int i = 0; i < servicos.size(); i++) {
			servicos.get(i).setDataPagamento(new Date());
			servicoRepository.save(servicos.get(i));
		}

	}
	@Transactional(readOnly = false)
	public void salvarOuEditarVistoria(ServicoEmpresa servico) {
		for(int i = 0; i < servico.getOcorrencias().size() ; i++){
			servico.getOcorrencias().get(i).setData(new Date());
			servico.getOcorrencias().get(i).setUsuario(SessionUsuario.getInstance().getUsuario());
			servico.getOcorrencias().get(i).setServicoEmpresa(servico);
		}
		if(servico.getPorcentagem() == 100){
			servico.setDataEncerramentoServico(new Date());
		}
		servicoRepository.save(servico);
		
	}
	
	public Page<ServicoEmpresa> findAll(Pageable page) {
		return servicoRepository.findAll(page);
	}

	public Page<ServicoEmpresa> findByDescricaoIgnoreCase(String descricao, Pageable page) {
		Page<ServicoEmpresa> list = null;
		descricao = descricao.replaceAll("[./-]","");
		if (descricao.matches("[0-9]+")) {
			list = servicoRepository.findById(descricao, page);
		}else {
			list = servicoRepository.findByEstruturaDescricaoIgnoreCaseContaining(descricao, page);
		}
		if(list == null || list.getNumberOfElements() < 1) {
			throw new MensagemException("Não foi encontrado nenhuma resultado para a busca" + descricao);
		}
		return list;
	}
}
