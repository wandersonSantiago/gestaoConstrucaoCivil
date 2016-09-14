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
    private ConfigEmpreendimentoEdificioRepository configEdificioRepository;
    
    @Transactional(readOnly = false)
    public void salvarOuEditar(ConfigEmpreendimentoEdificio configEmpreendimentoEdificio)
    {
    	configEdificioRepository.save(configEmpreendimentoEdificio);
    }
     
    public List<ConfigEmpreendimentoEdificio> buscarTodos(){
         
        return configEdificioRepository.findAll();
    }
    
    public ConfigEmpreendimentoEdificio buscarPorId(Long id)
    {
        return configEdificioRepository.findOne(id);
    }
    public ConfigEmpreendimentoEdificioPojo getConfig()
    {
          ConfigEmpreendimentoEdificio edificio = configEdificioRepository.findByEmpreendimentoId(7L);
          return new ConfigEmpreendimentoEdificioPojo(edificio);
    
    }
 
}
