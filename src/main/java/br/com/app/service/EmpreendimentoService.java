package br.com.app.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.Empreendimento;
import br.com.app.entity.Usuario;
import br.com.app.exceptions.NotFoundException;
import br.com.app.pojo.SessionUsuario;
import br.com.app.repository.EmpreendimentoRepository;


@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class EmpreendimentoService {

	@Autowired
	private EmpreendimentoRepository empreendimentoRepository;



	@Transactional(readOnly = false)
	public Empreendimento insert(Empreendimento empreendimento){
		Usuario usuario = SessionUsuario.getInstance().getUsuario();
		
		if(!usuario.getLogin().equalsIgnoreCase("root")) {
			
			if(!usuario.getEmpreendimento().isMatriz()) {
					throw new NotFoundException("Você não tem permissão para cadastrar um empreendimento!! ");
						
			}
			empreendimento.setMatriz(usuario.getEmpreendimento());		
		}
		
		return empreendimentoRepository.save(empreendimento);
	}
	
	
	public Optional<Empreendimento> findById(Long id){
		
		Optional<Empreendimento> empreendimento = empreendimentoRepository.findById(id);
		
		if(empreendimento == null) {
			throw new NotFoundException("Não foi possivel encontrar nenhuma empreendimento com o ID: " + id);
		}
		return empreendimento;
	}

	
	public Page<Empreendimento> findByNomeFantasiaOrRazaoSocialOrCnpjIgnoreCase(String descricao, Pageable page){
		
		Page<Empreendimento> empreendimentos = empreendimentoRepository.findByDescricaoIgnoreCase(descricao, page);
	
		if(empreendimentos.getNumberOfElements() < 1  || empreendimentos == null) {
			throw new NotFoundException("Não foi possivel encontrar nenhuma empreendimento com esta descrição: " + descricao);
		}
		return empreendimentos;
		
	}
	
	
	public Page<Empreendimento> findAll(PageRequest pageRequest) {
		
		Page<Empreendimento> empreendimentos =  empreendimentoRepository.findAll(pageRequest);
		if(empreendimentos.getNumberOfElements() < 1 || empreendimentos == null) {
			throw new NotFoundException("Não existe empreendimento cadastrada");
		}
		return empreendimentos;
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
