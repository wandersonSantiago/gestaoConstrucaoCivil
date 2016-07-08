package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.repository.EmpreendimentoRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class EmpreendimentoService {

	@Autowired
	private EmpreendimentoRepository empreendimentoRepository;

    public List<Empreendimento> buscarTodos() {
		
		return empreendimentoRepository.findAll();
	}
    
    @Transactional(readOnly = false)
    public void salvarOuEditar(Empreendimento empreendimento)
    {
    	empreendimentoRepository.save(empreendimento);
    }
    
}
