package br.com.app.service.almoxarifado;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.almoxarifado.Fornecedor;
import br.com.app.repository.almoxarifado.FornecedorRepository;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;

	public List<Fornecedor> buscarTodos() {

		return fornecedorRepository.findAll();
	}
	@Transactional(readOnly = false)
	public void salvarOuEditar(Fornecedor fornecedor)
	{
		fornecedorRepository.save(fornecedor);
	}
	public Optional<Fornecedor> buscarPorId(Long id) {
		
		return fornecedorRepository.findById(id);
	}
}
