package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.Permissao;
import br.com.system.gestaoConstrucaoCivil.repository.PermissaoRepository;

@Service
@Transactional(readOnly=true, propagation = Propagation.REQUIRED)
public class PermissaoService {

	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@Transactional(readOnly=false)
	public void salvar(Permissao permissao){
		permissaoRepository.save(permissao);
	}
	
	public Permissao buscarPorId(Long id){
		return permissaoRepository.findOne(id);
	}
	
	public List<Permissao>  lista(){
		return permissaoRepository.findAll();
	}
}
