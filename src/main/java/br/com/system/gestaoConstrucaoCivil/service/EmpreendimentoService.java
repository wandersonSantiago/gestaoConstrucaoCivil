package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.repository.EmpreendimentoRepository;

@Service
@Transactional
public class EmpreendimentoService {

	@Autowired
	private EmpreendimentoRepository empreendimentoRepository;

    public List<Empreendimento> buscarTodos() {
		
		return empreendimentoRepository.findAll();
	}
    
}
