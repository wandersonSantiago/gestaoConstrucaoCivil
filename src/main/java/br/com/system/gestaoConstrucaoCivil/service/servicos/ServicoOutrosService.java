package br.com.system.gestaoConstrucaoCivil.service.servicos;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.servicos.ServicoOutros;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;
import br.com.system.gestaoConstrucaoCivil.repository.servicos.ServicoOutrosRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class ServicoOutrosService {

	@Autowired
	private ServicoOutrosRepository servicoOutrosRepository;
	
	//@Autowired
	//private ValidacaoServico<ServicoOutros> validacao;
	
	public Page<ServicoOutros> buscarTodos(PageRequest pages) {		
		return servicoOutrosRepository.findAll(pages);
	}
	
	@Transactional(readOnly = false)
	public void  salvarOuEditar(ServicoOutros servico)	{
		servico.setEmpreendimento(SessionUsuario.getInstance().getUsuario().getEmpreendimento());
		//validacao.verificarExistePacoteParaEmpresa(servico);
		servicoOutrosRepository.save(servico);
	}
	public Optional<ServicoOutros> buscarPorId(Long id) {
		return servicoOutrosRepository.findById(id);
	}
	public Collection<ServicoOutros> lista() {
		return servicoOutrosRepository.findAll();
	}

	public Collection<ServicoOutros> buscarServicoComunitario(String outros) {
		return servicoOutrosRepository.findByDescricao(outros);
	}
}
