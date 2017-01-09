package br.com.system.gestaoConstrucaoCivil.service.servicos;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.entity.servicos.PacoteServico;
import br.com.system.gestaoConstrucaoCivil.repository.servicos.PacoteServicoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class PacoteServicoService {

	@Autowired
	private PacoteServicoRepository pacoteServicoRepository;

	public List<PacoteServico> buscarTodos() {

		return pacoteServicoRepository.findAll();
	}

	@Transactional(readOnly = false)
	public void salvarOuEditar(PacoteServico pacoteServico) {
		pacoteServico.novoPacote();
		pacoteServicoRepository.save(pacoteServico);
	}

	public PacoteServico buscarPorId(Long id) {

		return pacoteServicoRepository.findOne(id);
	}
}
