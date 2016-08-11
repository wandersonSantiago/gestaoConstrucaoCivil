package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.ConfigEmpreendimentoOutros;
import br.com.system.gestaoConstrucaoCivil.repository.ConfigEmpreendimentoOutrosRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class ConfigEmpreendimentoOutrosService {
	

    @Autowired
    private ConfigEmpreendimentoOutrosRepository configEmpreendimentoOutrosRepository;
     
    @Transactional(readOnly = false)
    public void salvarOuEditar(ConfigEmpreendimentoOutros configEmpreendimentoOutros)
    {
    	configEmpreendimentoOutrosRepository.save(configEmpreendimentoOutros);
    }
     
    public List<ConfigEmpreendimentoOutros> buscarTodos(){
         
        return configEmpreendimentoOutrosRepository.findAll();
    }
    public ConfigEmpreendimentoOutros buscarPorId(Long id)
    {
        return configEmpreendimentoOutrosRepository.findOne(id);
    }
   

}
