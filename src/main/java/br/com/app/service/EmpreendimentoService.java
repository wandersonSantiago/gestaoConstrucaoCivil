package br.com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.Empreendimento;
import br.com.app.pojo.SessionUsuario;
import br.com.app.repository.EmpreendimentoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class EmpreendimentoService {

	@Autowired
	private EmpreendimentoRepository empreendimentoRepository;

	public List<Empreendimento> buscarTodos() {

		return empreendimentoRepository.findAll();
	}

	public Optional<Empreendimento> buscarPorId(Long id) {
		return empreendimentoRepository.findById(id);
	}

	public List<Empreendimento> buscaEmpreendimentoSemConfiguracao() {
		return empreendimentoRepository.buscaEmpreendimentoSemConfiguracao();
	}

	@Transactional(readOnly = false)
	public void salvarOuEditar(Empreendimento empreendimento) {
		empreendimentoRepository.save(empreendimento);
	}

	@Transactional(readOnly = false)
	public void adcionarValorGasto(Double valorGasto) {
		Long idEmpeendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();

		Empreendimento empreendimento = empreendimentoRepository.findById(idEmpeendimento).get();

		empreendimento.setValoresGastos(empreendimento.getValoresGastos() + valorGasto);
		empreendimentoRepository.save(empreendimento);

	}

	@Transactional(readOnly = false)
	public void removerValorGasto(Double valorGasto) {
		Long idEmpeendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();

		Empreendimento empreendimento = empreendimentoRepository.findById(idEmpeendimento).get();

		empreendimento.setValoresGastos(empreendimento.getValoresGastos() - valorGasto);
		empreendimentoRepository.save(empreendimento);

	}

}
