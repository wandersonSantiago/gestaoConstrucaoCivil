package br.com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.Endereco;
import br.com.app.repository.EnderecoRepository;


@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(Endereco endereco)
	{
		enderecoRepository.save(endereco);
	}
}
