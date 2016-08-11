package br.com.system.gestaoConstrucaoCivil.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
 
import br.com.system.gestaoConstrucaoCivil.entity.ConfigEmpreendimento;
import br.com.system.gestaoConstrucaoCivil.repository.ConfigEmpreendimentoRepository;
 
@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class ConfigEmpreendimentoService {
 
    @Autowired
    private ConfigEmpreendimentoRepository configEmpreendimentoRepository;
     
    @Transactional(readOnly = false)
    public void salvarOuEditar(ConfigEmpreendimento configEmpreendimento)
    {
        configEmpreendimentoRepository.save(configEmpreendimento);
    }
     
    public List<ConfigEmpreendimento> buscarTodos(){
         
        return configEmpreendimentoRepository.findAll();
    }
    public ConfigEmpreendimento buscarPorId(Long id)
    {
        return configEmpreendimentoRepository.findOne(id);
    }
   
}