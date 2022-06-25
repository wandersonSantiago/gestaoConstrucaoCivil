package br.com.app.commons.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.commons.domain.model.ConfiguracaoEmpreendimento;
import br.com.app.commons.domain.model.Empreendimento;
import br.com.app.commons.domain.model.Usuario;
import br.com.app.commons.domain.repository.ConfiguracaoEmpreendimentoRepository;
import br.com.app.commons.domain.repository.EmpreendimentoRepository;
import br.com.app.financeiro.domain.enuns.DataBaseEnum;
import br.com.app.infraestructure.exception.NotFoundException;
import br.com.app.infraestructure.security.SessionUsuario;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class EmpreendimentoService {

	@Autowired
	private EmpreendimentoRepository empreendimentoRepository;
	@Autowired
	private ConfiguracaoEmpreendimentoRepository configuracaoEmpreendimentoRepository;
	
	@Transactional(readOnly = false)
	public Empreendimento insert(Empreendimento empreendimento) {
		Usuario usuario = SessionUsuario.getInstance().getUsuario();

		if (!usuario.getLogin().equalsIgnoreCase("root")) {

			if (!usuario.getEmpreendimento().isMatriz()) {
				throw new NotFoundException("Você não tem permissão para cadastrar um empreendimento!! ");

			}
			empreendimento.setMatriz(usuario.getEmpreendimento());
		}
		 empreendimentoRepository.save(empreendimento);
		 salvarConfiguracaoPadaro(empreendimento);
		return empreendimento;
	}

	private void salvarConfiguracaoPadaro(Empreendimento empreendimento) {
		ConfiguracaoEmpreendimento config = new ConfiguracaoEmpreendimento();
		config.setDataBaseFinanceiro(DataBaseEnum._1);
		config.setEmpreendimento(empreendimento);
		configuracaoEmpreendimentoRepository.save(config);
		
	}

	public Empreendimento findById(Long id) {

		return empreendimentoRepository.findById(id).orElseThrow(
				() -> new NotFoundException("Não foi possivel encontrar nenhuma empreendimento com o ID: " + id));
	}

	public Page<Empreendimento> findByDescricaoIgnoreCase(String descricao, Pageable page) {
		Page<Empreendimento> empreendimentos = null;

		Usuario user = SessionUsuario.getInstance().getUsuario();
		if (user.isRoot()) {
			empreendimentos = empreendimentoRepository.findByDescricaoContainsIgnoreCaseAndMatrizIsNull(descricao,
					page);
		} else {
			empreendimentos = empreendimentoRepository.findByDescricaoContainsIgnoreCase(descricao, page);
		}

		if (empreendimentos == null || empreendimentos.getNumberOfElements() < 1) {
			throw new NotFoundException(
					"Não foi possivel encontrar nenhuma empreendimento com esta descrição: " + descricao);
		}
		return empreendimentos;

	}

	public Page<Empreendimento> findAll(Pageable page) {

		Page<Empreendimento> empreendimentos = null;

		Usuario user = SessionUsuario.getInstance().getUsuario();
		if (user.isRoot()) {
			empreendimentos = empreendimentoRepository.findByMatrizIsNull(page);
		} else {
			empreendimentos = empreendimentoRepository.findAll(page);
		}

		if (empreendimentos == null || empreendimentos.getNumberOfElements() < 1) {
			throw new NotFoundException("Não existe empreendimento cadastrada");
		}
		return empreendimentos;
	}

}
