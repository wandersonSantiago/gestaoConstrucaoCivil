package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.entity.Funcionario;
import br.com.system.gestaoConstrucaoCivil.repository.FuncionarioRepository;


@Service
@Transactional
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public List<Funcionario> buscarTodos() {
	 		
	 		return funcionarioRepository.findAll();
	 }
}
