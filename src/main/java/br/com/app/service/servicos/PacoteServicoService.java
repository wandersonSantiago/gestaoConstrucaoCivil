package br.com.app.service.servicos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.servicos.PacoteServico;
import br.com.app.pojo.MensagemException;
import br.com.app.repository.servicos.PacoteServicoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class PacoteServicoService {

	@Autowired
	private PacoteServicoRepository pacoteServicoRepository;

	@Transactional(readOnly = false)
	public void salvarOuEditar(PacoteServico pacoteServico) {
		pacoteServico.novoPacote();
		pacoteServicoRepository.save(pacoteServico);
	}

	public Optional<PacoteServico> buscarPorId(Long id) {
		return pacoteServicoRepository.findById(id);
	}

	public Page<PacoteServico> findAll(Pageable page) {
		return pacoteServicoRepository.findAll(page);
	}

	public Page<PacoteServico> findByDescricaoIgnoreCase(String descricao, Pageable page) {
		Page<PacoteServico> list = null;
		descricao = descricao.replaceAll("[./-]","");
		if (descricao.matches("[0-9]+")) {
			list = pacoteServicoRepository.findByCodigo(descricao, page);
		}else {
			list = pacoteServicoRepository.findByDescricaoIgnoreCaseContaining(descricao, page);
		}
		if(list == null || list.getNumberOfElements() < 1) {
			throw new MensagemException("Não foi encontrado nenhuma resultado para a busca" + descricao);
		}
		return list;
	}
}
