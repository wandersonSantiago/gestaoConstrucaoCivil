package br.com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.Estrutura;
import br.com.app.entity.Usuario;
import br.com.app.exceptions.NotFoundException;
import br.com.app.pojo.SessionUsuario;
import br.com.app.repository.EstruturaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class EstruturaService {

	@Autowired
	private EstruturaRepository estruturaRepository;

	@Transactional(readOnly = false)
	public Estrutura insert(Estrutura estrutura) {
		Usuario user = SessionUsuario.getInstance().getUsuario();		
		if(!user.getEmpreendimento().isMatriz()) {
			if (estrutura.getRaiz() != null) {
				Estrutura raiz = estruturaRepository.findById(estrutura.getRaiz().getId()).get();				
				estrutura.setRaiz(raiz);
			}
			estrutura.setIdEmpreendimento(user.getEmpreendimento().getId());
			return estruturaRepository.save(estrutura);
		}
		throw new NotFoundException("Este empreendimento não pode ter uma estrutura: ");
	}

	public Optional<Estrutura> findById(Long id) {
		Usuario user = SessionUsuario.getInstance().getUsuario();
		Optional<Estrutura> estrutura = estruturaRepository.findByIdAndIdEmpreendimento(id, user.getEmpreendimento().getId());

		if (estrutura == null) {
			throw new NotFoundException("Não foi possivel encontrar nenhuma estrutura com o ID: " + id);
		}
		return estrutura;
	}

	public Page<Estrutura> findByDescricaoIgnoreCase(String descricao, Pageable page) {
		Usuario user = SessionUsuario.getInstance().getUsuario();
		
		Page<Estrutura> estruturas = estruturaRepository.findByDescricaoContainsIgnoreCaseAndIdEmpreendimento(descricao, user.getEmpreendimento().getId(), page);

		if (estruturas == null || estruturas.getNumberOfElements() < 1) {
			throw new NotFoundException(
					"Não foi possivel encontrar nenhuma estrutura com esta descrição: " + descricao);
		}
		return estruturas;

	}

	public Page<Estrutura> findAll(Pageable page) {
		Usuario user = SessionUsuario.getInstance().getUsuario();
		
		Page<Estrutura> estruturas = estruturaRepository.findByIdEmpreendimento(user.getEmpreendimento().getId(),page);
		if (estruturas.getNumberOfElements() < 1 || estruturas == null) {
			throw new NotFoundException("Não existe estrutura cadastrada");
		}
		return estruturas;
	}

	public List<Estrutura> findByEstruturasFolhas(Long id) {
		Usuario user = SessionUsuario.getInstance().getUsuario();
		
		List<Estrutura> estruturas = null;		
		if(id > 0) {
			estruturas =  estruturaRepository.findByRaizIdAndIdEmpreendimento(id, user.getEmpreendimento().getId());
		}else {
			estruturas =  estruturaRepository.findByRaizIsNullAndIdEmpreendimento(user.getEmpreendimento().getId());
		}
		if(estruturas.isEmpty() || estruturas == null) {
			throw new NotFoundException("Não existe estrutura filhas");
		}
		return estruturas;
	}

}
