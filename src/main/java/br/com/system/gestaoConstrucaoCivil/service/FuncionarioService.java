package br.com.system.gestaoConstrucaoCivil.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.entity.Funcionario;
import br.com.system.gestaoConstrucaoCivil.repository.FuncionarioRepository;


@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public List<Funcionario> buscarTodos() {
	 		
	 		return funcionarioRepository.findAll();
	 }
	public List<Funcionario> buscarFuncionarioEngenheiro()
	{
		return funcionarioRepository.findByCreaNotNull();
	}
	
	public Funcionario buscarFuncionarioPorId(Long id){
	
		return funcionarioRepository.findOne(id);
	}
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(Funcionario funcionario)
	{
		funcionarioRepository.save(funcionario);
	}
	
}
