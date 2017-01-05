package br.com.system.gestaoConstrucaoCivil.service.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.servicos.ServicoEdificio;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;
import br.com.system.gestaoConstrucaoCivil.repository.servicos.ServicoEdificioRepository;
import br.com.system.gestaoConstrucaoCivil.regras.servicos.ValidacaoServico;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ServicoEdificioService {

	@Autowired
	private ServicoEdificioRepository servicoEdificioRepository;

	@Autowired
	private ValidacaoServico<ServicoEdificio> validacao;
	
	public List<ServicoEdificio> buscarTodos() {

		return servicoEdificioRepository.findAll();
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
}