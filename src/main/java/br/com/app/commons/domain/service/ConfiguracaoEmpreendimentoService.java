package br.com.app.commons.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.commons.domain.model.ConfiguracaoEmpreendimento;
import br.com.app.commons.domain.repository.ConfiguracaoEmpreendimentoRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class ConfiguracaoEmpreendimentoService {

	@Autowired
	private ConfiguracaoEmpreendimentoRepository configuracaoEmpreendimentoRepository;
	
	@Transactional(readOnly = false)
	public void insert(ConfiguracaoEmpreendimento config){
		configuracaoEmpreendimentoRepository.save(config);
	}
	
	@Transactional(readOnly = false)
	public void update(ConfiguracaoEmpreendimento config){
		configuracaoEmpreendimentoRepository.save(config);
	}
	
    public List<ConfiguracaoEmpreendimento> findAll(){		
		return configuracaoEmpreendimentoRepository.findAll();
	}
    
    public ConfiguracaoEmpreendimento findById(Long id){
    	return configuracaoEmpreendimentoRepository.findById(id).get();
    }
    
    public ConfiguracaoEmpreendimento findByEmpreendimentoId(Long id){
    	return configuracaoEmpreendimentoRepository.findByEmpreendimentoId(id);
    }
}
