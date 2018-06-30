package br.com.app.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

	
	public Page<Empreendimento> findByDescricaoIgnoreCase(String descricao, Pageable page){
		Page<Empreendimento> empreendimentos = null ;
		
		Usuario user = SessionUsuario.getInstance().getUsuario();
		if(user.isRoot()) {
			empreendimentos =  empreendimentoRepository.findByDescricaoContainsIgnoreCaseAndMatrizIsNull(descricao, page);
		}else
			/*if(user.getEmpreendimento().isMatriz()){
			empreendimentos =  empreendimentoRepository.findByMatriz_idAndDescricaoContainsIgnoreCase(user.getEmpreendimento().getId(),descricao,page);
		}		*/
		{
			empreendimentos =  empreendimentoRepository.findByDescricaoContainsIgnoreCase(descricao,page);
		}
			
		if(empreendimentos == null || empreendimentos.getNumberOfElements() < 1) {
			throw new NotFoundException("Não foi possivel encontrar nenhuma empreendimento com esta descrição: " + descricao);
		}
		return empreendimentos;
		
	}
	
	
	public Page<Empreendimento> findAll(Pageable page) {
		
		Page<Empreendimento> empreendimentos = null;
				
		Usuario user = SessionUsuario.getInstance().getUsuario();
		if(user.isRoot()) {
			empreendimentos =  empreendimentoRepository.findByMatrizIsNull(page);
		}else {
			empreendimentos =  empreendimentoRepository.findAll(page);
		}
			/*if(user.getEmpreendimento().isMatriz()){
			empreendimentos =  empreendimentoRepository.findByMatriz_id(user.getEmpreendimento().getId(),page);
		}
		*/
		if( empreendimentos == null || empreendimentos.getNumberOfElements() < 1) {
			throw new NotFoundException("Não existe empreendimento cadastrada");
		}
		return empreendimentos;
	}
	

	

}
