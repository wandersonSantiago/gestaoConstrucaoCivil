package br.com.system.gestaoConstrucaoCivil.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.ConfigEmpreendimentoEdificio;
import br.com.system.gestaoConstrucaoCivil.pojo.ConfigEmpreendimentoEdificioPojo;
import br.com.system.gestaoConstrucaoCivil.repository.ConfigEmpreendimentoEdificioRepository;


@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class ConfigEmpreendimentoEdificioService {

	
	@Autowired
    private ConfigEmpreendimentoEdificioRepository configEmpreendimentoEdificioRepository;
     
    @Transactional(readOnly = false)
    public void salvarOuEditar(ConfigEmpreendimentoEdificio configEmpreendimentoEdificio)
    {
    	configEmpreendimentoEdificioRepository.save(configEmpreendimentoEdificio);
    }
     
    public List<ConfigEmpreendimentoEdificio> buscarTodos(){
         
        return configEmpreendimentoEdificioRepository.findAll();
    }
    
    public ConfigEmpreendimentoEdificio buscarPorId(Long id)
    {
        return configEmpreendimentoEdificioRepository.findOne(id);
    }
    public void ConfigEmpreendimentoEdificioPojo()
    {
    	
    }
 
}
