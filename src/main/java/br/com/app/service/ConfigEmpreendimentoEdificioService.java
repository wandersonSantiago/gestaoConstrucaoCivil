package br.com.app.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.ConfigEmpreendimentoEdificio;
import br.com.app.pojo.ConfigEmpreendimentoEdificioPojo;
import br.com.app.repository.ConfigEmpreendimentoEdificioRepository;


@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class ConfigEmpreendimentoEdificioService {

	
	@Autowired
    private ConfigEmpreendimentoEdificioRepository configEdificioRepository;
    
    @Transactional(readOnly = false)
    public void salvarOuEditar(ConfigEmpreendimentoEdificio configEmpreendimentoEdificio)
    {
    	configEdificioRepository.save(configEmpreendimentoEdificio);
    }
     
    public List<ConfigEmpreendimentoEdificio> buscarTodos(){
         
        return configEdificioRepository.findAll();
    }
    
    public Optional<ConfigEmpreendimentoEdificio> buscarPorId(Long id)
    {
        return configEdificioRepository.findById(id);
    }
    public ConfigEmpreendimentoEdificioPojo getConfig()
    {
    	//Remover depois   edificio = configEdificioRepository.findAll().get(0);
    	ConfigEmpreendimentoEdificio edificio = configEdificioRepository.findAll().get(0);
        return new ConfigEmpreendimentoEdificioPojo(edificio);
    
    }
 
}
