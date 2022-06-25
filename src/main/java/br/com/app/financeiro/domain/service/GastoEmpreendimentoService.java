package br.com.app.financeiro.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.commons.domain.model.Empreendimento;
import br.com.app.commons.domain.repository.EmpreendimentoRepository;
import br.com.app.infraestructure.exception.NotFoundException;
import br.com.app.infraestructure.security.SessionUsuario;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class GastoEmpreendimentoService {

	@Autowired
	private EmpreendimentoRepository empreendimentoRepository;

	@Transactional(readOnly = false)
	public void adicionarValorGasto(Double valorGasto) {
		Long idEmpeendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();

		Empreendimento empreendimento = empreendimentoRepository.findById(idEmpeendimento)
				.orElseThrow(() -> new NotFoundException("Não foi encontrado nenhum Empreendimento!"));

		empreendimento.setValoresGastos(empreendimento.getValoresGastos() + valorGasto);
		empreendimentoRepository.save(empreendimento);

	}

	@Transactional(readOnly = false)
	public void removerValorGasto(Double valorGasto) {
		Long idEmpeendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();

		Empreendimento empreendimento = empreendimentoRepository.findById(idEmpeendimento)
				.orElseThrow(() -> new NotFoundException("Não foi encontrado nenhum Empreendimento!"));

		empreendimento.setValoresGastos(empreendimento.getValoresGastos() - valorGasto);
		empreendimentoRepository.save(empreendimento);

	}
}
