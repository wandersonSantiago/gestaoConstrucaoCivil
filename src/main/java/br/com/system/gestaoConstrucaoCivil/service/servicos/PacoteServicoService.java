package br.com.system.gestaoConstrucaoCivil.service.servicos;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.servicos.PacoteServico;
import br.com.system.gestaoConstrucaoCivil.repository.servicos.PacoteServicoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class PacoteServicoService {

	@Autowired
	private PacoteServicoRepository pacoteServicoRepository;

	public Page<PacoteServico> buscarTodos(PageRequest page) {
		return pacoteServicoRepository.findAll(page);
	}

	@Transactional(readOnly = false)
	public void salvarOuEditar(PacoteServico pacoteServico) {
		pacoteServico.novoPacote();
		pacoteServicoRepository.save(pacoteServico);
	}

	public Optional<PacoteServico> buscarPorId(Long id) {

		return pacoteServicoRepository.findById(id);
	}

	public Collection<PacoteServico> lista() {
		return pacoteServicoRepository.findAll();
	}
}
