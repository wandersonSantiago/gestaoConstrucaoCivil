package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.ConfigEmpreendimentoCasa;
import br.com.system.gestaoConstrucaoCivil.pojo.ConfigEmpreendimentoCasaPojo;
import br.com.system.gestaoConstrucaoCivil.repository.ConfigEmpreendimentoCasaRepository;


@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class ConfigEmpreendimentoCasaService {

	

	@Autowired
    private ConfigEmpreendimentoCasaRepository configEmpreendimentoCasaRepository;
     
    @Transactional(readOnly = false)
    public void salvarOuEditar(ConfigEmpreendimentoCasa configEmpreendimentoCasa)
    {
    	configEmpreendimentoCasaRepository.save(configEmpreendimentoCasa);
    }
     
    public List<ConfigEmpreendimentoCasa> buscarTodos(){
         
        return configEmpreendimentoCasaRepository.findAll();
    }
    
    public ConfigEmpreendimentoCasa buscarPorId(Long id)
    {
        return configEmpreendimentoCasaRepository.findOne(id);
    }
    
    public ConfigEmpreendimentoCasaPojo getConfig()
    {
    	ConfigEmpreendimentoCasa casa = configEmpreendimentoCasaRepository.findAll().get(0);
        return new ConfigEmpreendimentoCasaPojo(casa);
    
    }
}
