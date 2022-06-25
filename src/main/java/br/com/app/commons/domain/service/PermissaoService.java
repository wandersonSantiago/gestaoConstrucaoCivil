package br.com.app.commons.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.commons.domain.model.Permissao;
import br.com.app.commons.domain.repository.PermissaoRepository;

@Service
@Transactional(readOnly=true, propagation = Propagation.REQUIRED)
public class PermissaoService {

	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Transactional(readOnly=false)
	public void insert(Permissao permissao){
		permissaoRepository.save(permissao);
	}
	
	public Optional<Permissao> buscarPorId(Long id){
		return permissaoRepository.findById(id);
	}
	
	public List<Permissao>  lista(){
		return permissaoRepository.findAll();
	}
}
