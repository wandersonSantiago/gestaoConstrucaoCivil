package br.com.system.gestaoConstrucaoCivil.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.Pessoa;
import br.com.system.gestaoConstrucaoCivil.repository.PessoaRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(Pessoa pessoa)
	{
	    pessoaRepository.save(pessoa);	
	}
}
