package br.com.system.gestaoConstrucaoCivil.service.servicos;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.servicos.ServicoCasa;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;
import br.com.system.gestaoConstrucaoCivil.repository.servicos.ServicoCasaRepository;

@Service
@Transactional(readOnly = true)
public class ServicoCasaService {

	@Autowired
	private ServicoCasaRepository servicoCasaRepository;

	// @Autowired
	// private ValidacaoServico<ServicoCasa> validacao;

	public Page<ServicoCasa> buscarTodos(PageRequest pages) {

		return servicoCasaRepository.findAll(pages);
	}

	@Transactional(readOnly = false)
	public void salvarOuEditar(ServicoCasa servico) {
		servico.setEmpreendimento(SessionUsuario.getInstance().getUsuario().getEmpreendimento());
		// validacao.verificarExistePacoteParaEmpresa(servico);
		servicoCasaRepository.save(servico);
	}

	public Optional<ServicoCasa> buscarPorId(Long id) {
		return servicoCasaRepository.findById(id);
	}

	public Collection<ServicoCasa> lista() {
		return servicoCasaRepository.findAll();
	}

}
