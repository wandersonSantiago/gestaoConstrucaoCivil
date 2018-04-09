package br.com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.Funcionario;
import br.com.app.repository.FuncionarioRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public List<Funcionario> buscarTodos() {

		return funcionarioRepository.findAll();
	}

	public List<Funcionario> buscarFuncionarioEngenheiro() {
		return funcionarioRepository.findByCreaNotNull();
	}

	public Optional<Funcionario> buscarFuncionarioPorId(Long id) {

		return funcionarioRepository.findById(id);
	}

	@Transactional(readOnly = false)
	public void salvarOuEditar(Funcionario funcionario) {
		funcionarioRepository.save(funcionario);
	}

	public Page<Funcionario> listaComPaginacao(PageRequest pageRequest) {
		return funcionarioRepository.findAll(pageRequest);
	}

}
