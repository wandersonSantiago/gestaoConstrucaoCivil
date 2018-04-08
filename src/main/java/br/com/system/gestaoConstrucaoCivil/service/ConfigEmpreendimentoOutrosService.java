package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.ConfigEmpreendimentoOutros;
import br.com.system.gestaoConstrucaoCivil.repository.ConfigEmpreendimentoOutrosRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ConfigEmpreendimentoOutrosService {

	@Autowired
	private ConfigEmpreendimentoOutrosRepository configEmpreendimentoOutrosRepository;

	@Transactional(readOnly = false)
	public void salvarOuEditar(List<ConfigEmpreendimentoOutros> configEmpreendimentoOutros) {
		configEmpreendimentoOutrosRepository.saveAll(configEmpreendimentoOutros);
	}

	public List<ConfigEmpreendimentoOutros> buscarTodos() {

		return configEmpreendimentoOutrosRepository.findAll();
	}

	public Optional<ConfigEmpreendimentoOutros> buscarPorId(Long id) {
		return configEmpreendimentoOutrosRepository.findById(id);
	}

}
