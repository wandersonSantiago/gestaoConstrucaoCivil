package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.entity.TipoEmpreendimento;
import br.com.system.gestaoConstrucaoCivil.repository.TipoEmpreendimentoRepository;

@Service
@Transactional
public class TipoEmpreendimentoService {


	@Autowired
	private TipoEmpreendimentoRepository tipoEmpreendimentoRepository;
	
     public List<TipoEmpreendimento> buscarTodos() {
		
		return tipoEmpreendimentoRepository.findAll();
	}
    
}
