package br.com.app.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.Pessoa;
import br.com.app.repository.PessoaRepository;

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
	
	public boolean existeCpf(String cpf){
		
		return pessoaRepository.existCpf(cpf);
	}
	public boolean existeRg(String rg){
		
		return pessoaRepository.existRg(rg);
	}
}
