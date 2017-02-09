package br.com.system.gestaoConstrucaoCivil.service.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.servicos.ServicoOutros;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;
import br.com.system.gestaoConstrucaoCivil.regras.servicos.ValidacaoServico;
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
	public ServicoOutros buscarPorId(Long id) {
		return servicoOutrosRepository.findOne(id);
	}
	public Iterable<ServicoOutros> lista() {
		return servicoOutrosRepository.findAll();
	}
}
