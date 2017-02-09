package br.com.system.gestaoConstrucaoCivil.service.servicos;

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

	@Transactional(readOnly = false)
	public void salvarOuEditar(ServicoEdificio servico) {
		servico.setEmpreendimento(SessionUsuario.getInstance().getUsuario().getEmpreendimento());
		validacao.verificarExistePacoteParaEmpresa(servico);
		servicoEdificioRepository.save(servico);

	}
	public ServicoEdificio buscarPorId(Long id) {
		
		return servicoEdificioRepository.findOne(id);
	}

	public Iterable<ServicoEdificio> lista() {
		return servicoEdificioRepository.findAll();
	}
}